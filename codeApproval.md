# Code approval and code reviews

## Before approval:
Keep your pieces of code small, try not to commit more than 400 lines of code each time, to keep the code review quality good.
Make sure you write tests for the code you wrote and integrate them in the CI.

##### Not being able to provide in these requirements per merge or push requests should be rejected by other devs.

## During approval:
Thoroughly check others code and check if:
- it compiles
- it is styled nicely
- the packages, classes, variables, and functions follow the naming convention
- the dev has written enough tests, and the test coverage is above 80% at least
- there is no unnecessary code, like unused variables or dead end functions

## After approval:
Check if the code is properly integrated, if CI doesn't cover the correct part of the codebase the dev should test it is as a whole themselves.