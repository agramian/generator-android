# Android generator

Generates a minimal Android app using the Yeoman scaffolding tool.

### Usage

Install `npm`

Install yo with `npm install -g yo`

Install this package with `npm -g install @agramian/generator-android`

Create a destination directory for the new project and `cd` into it then run `yo @agramian/android`

### Signing your app

Default `keystore.properties` and `keystore/android.jks` files are provided to allow signing and running your app in release mode. These files are added to the `.gitignore` by default to prevent access to them in case your repository is public.

Make sure to update these files if you decide to publish your app or library.

To generate a new keystore run the following command and follow the prompts:

`keytool -genkey -v -keystore android.jks -alias [DESIRED_ALIAS_NAME] -keyalg RSA -keysize 2048 -validity 10000`

Once the file is generated update the values in `keystore.properties`.

See https://developer.android.com/studio/publish/app-signing.html for more info.
