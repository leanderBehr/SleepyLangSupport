# SleepyLangSupport

A language support plugin for intellij based IDEs for the Sleepy language based on its compiler. A prebuilt archives are available in this repository in `distributions`.

### Features
* Simple (lexer based) syntax highlighting.
* (__CLion only__) Enables debugging executables with the built in gdb interface.
* (__In Progress__ [on this branch](https://github.com/leanderBehr/SleepyLangSupport/tree/parsing)) Semantic (parser based) syntax highlighting.

## How to use
1. Install from the latest archive in `distributions` or build yourself.
2. Then specify the path to the Sleepy compiler (i.e. the root of [the sleepy repository](https://github.com/Zettelkasten/sleepy)) in Settings -> Tools -> Sleepy Path.
3. If the dependencies of the sleepy compiler are not globally installed, also specify the additional python include path as e.g. `path/to/venv/lib/python3.8/site-packages`.
