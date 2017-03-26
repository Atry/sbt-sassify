#!/bin/bash

cd /sass/sassify

rm -r src/main/resources/linux-x86-64
mkdir -p src/main/resources/linux-x86-64

# *** Build libsass
make -C src/native clean
cd src/native
git reset --hard # hard reset
git clean -xdf # hard clean
cd ../..

BUILD="shared" \
CC=gcc \
CXX=g++ \
make -C src/native -j8 || exit 1

# *** Copy to target location
cp src/native/lib/libsass.so src/main/resources/linux-x86-64/libsass.so || exit 1

# *** Cleanup
cd /sass/sassify/src/native
git clean -xdf