#!/bin/bash
# Usage: publish.sh pathToUploadFile package username apikey owner repo version
API=https://api.bintray.com

function publishRCP() {
if [ ! -z "$1" ]; then FILE_TO_UPLOAD=$1; fi
if [ ! -z "$2" ]; then BINTRAY_PCK_NAME=$2; fi
if [ ! -z "$3" ]; then BINTRAY_USER=$3; fi
if [ ! -z "$4" ]; then BINTRAY_API_KEY=$4; fi
if [ ! -z "$5" ]; then BINTRAY_OWNER=$5; fi
if [ ! -z "$6" ]; then BINTRAY_REPO=$6; fi
if [ ! -z "$7" ]; then BINTRAY_PCK_VERSION=$7; fi

echo "FILE_TO_UPLOAD=$FILE_TO_UPLOAD"
echo "BINTRAY_PCK_NAME=$BINTRAY_PCK_NAME"
echo "BINTRAY_USER=$BINTRAY_USER"
echo "BINTRAY_OWNER=$BINTRAY_OWNER"
echo "BINTRAY_REPO=$BINTRAY_REPO"
echo "BINTRAY_PCK_VERSION=$BINTRAY_PCK_VERSION"

TAG=$(git describe --tags --exact-match 2>/dev/null)
TAG=${TAG#v}

if [ ! -z "$TAG" ]; then
  BINTRAY_PCK_VERSION=$TAG
fi

if [ ! -z "$BINTRAY_API_KEY" ]; then
  BINTRAY_API_KEY=$secure
fi

#if [ ! -z "$REPOSITORY_PATH" ]; then
#  pushd "$REPOSITORY_PATH"
#else
#  pushd .
#fi

#FILES=(content.jar artifacts.jar plugins/* features/* binary/*)

BINTRAY_PATH="$BINTRAY_OWNER/$BINTRAY_REPO/$BINTRAY_PCK_NAME/$BINTRAY_PCK_VERSION"
BINTRAY_OPTS="bt_package=$BINTRAY_PCK_NAME;bt_version=$BINTRAY_PCK_VERSION;publish=0"

# create version
curl -X POST -u ${BINTRAY_USER}:${BINTRAY_API_KEY} "${API}/packages/$BINTRAY_OWNER/$BINTRAY_REPO/$BINTRAY_PCK_NAME/versions" -d "{ \"name\": \"$BINTRAY_PCK_VERSION\" }" -H "Content-Type: application/json"

# upload files
#for f in ${FILES[@]}
#do
#if [ -f $f ]; then
  echo "Processing file: $FILE_TO_UPLOAD"
  curl -X PUT -T $FILE_TO_UPLOAD --retry 3 -u ${BINTRAY_USER}:${BINTRAY_API_KEY} "${API}/content/$BINTRAY_PATH/$f;$BINTRAY_OPTS"
#fi
#done

echo "Publishing the new version"
curl -X POST --retry 3 -u ${BINTRAY_USER}:${BINTRAY_API_KEY} "${API}/content/$BINTRAY_OWNER/$BINTRAY_REPO/$BINTRAY_PCK_NAME/$BINTRAY_PCK_VERSION/publish" -d "{ \"discard\": \"false\" }" -H "Content-Type: application/json"

popd
}

publishRCP "$@"