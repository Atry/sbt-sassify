#!/usr/bin/env bash

docker run --rm -v $(pwd)/bin/docker/buildWin32/x86/:/Compile -v $(pwd):/sass libsass/windows:1
docker run --rm -v $(pwd)/bin/docker/buildWin32/x86-64/:/Compile -v $(pwd):/sass libsass/windows:1
docker run --rm -v $(pwd)/bin/docker/buildDarwin:/Compile -v $(pwd):/sass libsass/darwin:1

bin/docker/buildLinux/x86/dockcross make shared -C src/native/
cp src/native/lib/libsass.so src/main/resources/linux-x86/libsass.so
make -C src/native/ clean

bin/docker/buildLinux/x86-64/dockcross make shared -C src/native/
cp src/native/lib/libsass.so src/main/resources/linux-x86-64/libsass.so
make -C src/native/ clean

