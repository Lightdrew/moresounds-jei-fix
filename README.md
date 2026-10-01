## More Sounds: JEI compatibility fix

Fixes a crash due to More Sounds failing to apply its mixin on newer JEI versions.\
Since More Sounds handles JEI compatibility via mixin it is susceptible to JEI changing its internals.\
\
This patch cancels More Sounds's Pseudo Mixin via MixinSquared whenever the inventory is opened\
and uses a JEIPlugin instead, removing such susceptibility to internal updates.\
\
This is a temporary fix until [this pull request](https://github.com/DVOA1/More-Sounds/pull/14) is finally merged into More Sounds itself.
