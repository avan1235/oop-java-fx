# Tiny Math

## Task description

Your goal is to write simple UI application that would allow you to solve math equations.
We want to reuse our model of calculations that was designed a few weeks ago and try to
use it in the context of UI application.
We want to have:
- `+`, `-`, `*` and `/` binary operations
- `cos`, `sin` and `ln` unary operations
- numbers (**in the UI** we can limit ourselves to single digit so possible input will be numbers `0`-`9`)
- single variable `x`
- derivative operation
- value in specified point operation
- placeholder to wait for user input (showing whitespace in the UI and throwing exception when actually used instead
  of being replaced by actual operation)

Your task is to:
1. Write a model for this application that will be independent of the UI. It should be placed in separate package
   (e.g. `pl.edu.mimuw.model`) and should have at least 3 tests in `DerivativeTest` class.
2. Write UI for the model. The UI may contain buttons for operations, digits, variable and calculation of
   the derivative and function value. **We can simplify** our approach here by restricting user to input
   his function in specified way - he needs to build the math expression as `tree`, so for example,
   to input `cos(x * x) + ln(x)` he would need to click
   1. `+`
   2. `Build Left`
   3. `cos`
   4. `Build child`
   5. `*`
   6. `Build Left`
   7. `x`
   8. `Left Done`
   9. `Build Right`
   10. `x`
   11. `Right Done`
   12. `Child Done`
   13. `Left Done`
   14. `Build Right`
   15. `ln`
   16. `Build Child`
   17. `x`
   18. `Child Done`
   19. `Right Done`

   where we need to use our placeholder for yet not-built child in tree representation and allow go through
   the tree structure with extra buttons `Build Left`, `Build Right`, `Build Child`, `Left Done`, `Right Done`, `Child
   Done`.
   Of course, if you can think of some simpler/better input method for this kind of expressions, you can design them
   using your own idea.

## Hints

- first let's take a look at last commit introducing this task to see what is needed to work with JavaFX application
in Gradle project - you can find it here.
- you can find sample app in `Main` class - it creates an application with some buttons and text area
which are properly aligned and have some actions assigned to them.
- ou should start your application with `run` task. As usual, you can start it from console with
  ```shell
  ./gradlew run
  ```
  but also you can run it from IDE by clicking `Ctrl` twice and inputting `gradlew run` as task name - this
  will create extra run configuration in the IDE, and you'll be able to run your app with green triangle
  button in top-right corner (as well as debug your application).
- be aware of Java version used in this task (there is some mess in terms of JavaFX) - I used Java 11 and
it worked well, so start with checking the `File/Project structure` settings if you have the same version.
- don't forget about providing required unit tests of your model to show me that you already know how to
write these tests.
- feel free to ask questions as there may be new things - [here](https://jenkov.com/tutorials/javafx/index.html)
you can find a good description and examples of the usage of JavaFX - let's take a look there to find some
new controls for your application.
