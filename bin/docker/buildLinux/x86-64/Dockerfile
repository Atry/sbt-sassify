FROM centos:6
MAINTAINER "Han van Venrooij" <clerris@gmail.com>

RUN set -x \
    && yum install -y wget

# Install build tools
RUN set -x \
    && cd /etc/yum.repos.d \
    && wget http://people.centos.org/tru/devtools-1.1/devtools-1.1.repo -O devtools-1.1.repo \
    && yum install -y devtoolset-1.1 devtoolset-1.1-gcc devtoolset-1.1-gcc-c++ glibc glibc-devel git \
    && scl enable devtoolset-1.1 bash

# Set PATH, because "scl enable" does not have any effects to "docker build"
ENV PATH $PATH:/opt/centos/devtoolset-1.1/root/usr/bin

# Run configuration
WORKDIR /sass
CMD ["/Compile/make.sh"]