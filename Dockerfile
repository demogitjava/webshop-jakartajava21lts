FROM i386/alpine AS BUILDER

RUN echo "http://dl-cdn.alpinelinux.org/alpine/edge/main" >> /etc/apk/repositories
RUN echo "http://dl-cdn.alpinelinux.org/alpine/edge/community" >> /etc/apk/repositories
RUN apk update
 
RUN echo 'root:jj78mvpr52k1' | chpasswd

# install tools
RUN apk add --no-cache \
  wget curl sudo vim file tzdata


ARG ARG_TZ="Europe/Berlin"
RUN ln -snf /usr/share/zoneinfo/$ARG_TZ /etc/localtime && \
  echo $ARG_TZ > /etc/timezone
  

# folder name
# graalvm-community-openjdk-21.0.1+12.1

# https://github.com/sgerrand/alpine-pkg-glibc/releases/download/2.35-r1/glibc-2.35-r1.apk
ENV GLIBC_VERSION=2.35-r1 \
    GRAALVM_VERSION=21.0.1+12.1 \
    JAVA_HOME=/usr/lib/jvm/graalvm-community-openjdk-${GRAALVM_VERSION} \
    PATH=/usr/lib/jvm/graalvm-ce-${GRAALVM_VERSION}/bin:$PATH

RUN apk --no-cache add ca-certificates wget gcc zlib zlib-dev libc-dev
#url graalvmce-11
# http://demogitjava.ddns.net:8000/Java_JDK/graalvm-community-jdk-21.0.1_linux-x64_bin.tar.gz

RUN mkdir /usr/lib/jvm; 
RUN wget "http://demogitjava.ddns.net:8000/Java_JDK/graalvm-community-jdk-21.0.1_linux-x64_bin.tar.gz"
RUN tar -zxC /usr/lib/jvm -f graalvm-community-jdk-21.0.1_linux-x64_bin.tar.gz
RUN rm -f graalvm-community-jdk-21.0.1_linux-x64_bin.tar.gz

RUN wget -q -O /etc/apk/keys/sgerrand.rsa.pub https://alpine-pkgs.sgerrand.com/sgerrand.rsa.pub \
    &&  wget "https://github.com/sgerrand/alpine-pkg-glibc/releases/download/$GLIBC_VERSION/glibc-$GLIBC_VERSION.apk" \
    &&  apk --no-cache add "glibc-$GLIBC_VERSION.apk" \
    &&  rm "glibc-$GLIBC_VERSION.apk" \
    &&  wget "https://github.com/sgerrand/alpine-pkg-glibc/releases/download/$GLIBC_VERSION/glibc-bin-$GLIBC_VERSION.apk" \
    &&  apk --no-cache add "glibc-bin-$GLIBC_VERSION.apk" \
    &&  rm "glibc-bin-$GLIBC_VERSION.apk" \
    &&  wget "https://github.com/sgerrand/alpine-pkg-glibc/releases/download/$GLIBC_VERSION/glibc-i18n-$GLIBC_VERSION.apk" \
    &&  apk --no-cache add "glibc-i18n-$GLIBC_VERSION.apk" \
    &&  rm "glibc-i18n-$GLIBC_VERSION.apk"