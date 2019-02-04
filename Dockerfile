FROM openjdk:8
ENV SDK_LINK https://dl.google.com/android/repository/sdk-tools-linux-4333796.zip
ENV ANDROID_HOME /android_sdk
ENV PATH=$PATH:$ANDROID_HOME/tools/bin:$ANDROID_HOME/platform-tools

RUN mkdir ~/.android && touch ~/.android/repositories.cfg

RUN mkdir android_sdk \
&& cd android_sdk \
&& wget $SDK_LINK -O sdk.zip \
&& unzip sdk.zip \    
&& cd tools/bin \
&& echo y | ./sdkmanager "platform-tools" "build-tools;28.0.0" \
&& echo y | ./sdkmanager --licenses



