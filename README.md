# Issue with Ktor Webpack and abort-controler

## Description of problem

Ktor is using abort-controller in this way
https://github.com/ktorio/ktor/blob/f1b970e6f925a8dbf3f1bea49a4928bf76f22292/ktor-client/ktor-client-core/js/src/io/ktor/client/engine/js/compatibility/Utils.kt#L46

This works fine on node targets, but due to the system I am running
my project on, and not all of ktor dependencies being in npm, I need to 
package all my dependencies into a single js file. To do this
I am using the browser target and customizing the webpack.config
to target nodejs. For some reason the way ktor is loading 
abort-controler after being processed through webpack it errors
out with the following error.
```
TypeError: controller is not a constructor
``` 

I have turned webpack and dce to dev mode to help diagnose the issue.

## Running

`./gradlew nodeRun` to see this working on node js
`./gradlew runWebpackedNode` to see this failing
