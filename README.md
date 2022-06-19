# Tiny Math By Jan Szot 430165

## Instructions

the main expression - the one you see when you input something via provided buttons cannot be extended,
what it means is that to input let's say `cos(x * x) + ln(x)`, we are going to click
1. `+`
2. `cos`
3. `*`
4. `x`
5. `x`
6. `ln`
7. `x`

The UI will help you understand what you need to click next by outputting `...` in all the places missing expressions.
The next thing you click will be put directly into the first missing expression slot. (Apart from clicking `=`, but we will get to that later)
Additionally when the main expression no longer has any missing expressions, `(C)` will be displayed.
In that state you can click certain button types:

  By clicking one of the one-argumented functions, these include
`sin`, `cos`, `ln` and `dx`, you will modify the main expression. By doing so your main expression will be taken as the argument for the function clicked.

By clicking a digit you will either input the value for x if there are any in the expression, or you will start a new expression and it will be just this digit.

By clicking one of the two-argumented function buttons, these include `*`, `-`, `/` and `+` you will restart the process.

By clicking equals one of three things will happen: if there are either no variables, or the value of the variables is initiated, the result will be shown, otherwise
the ui will ask you to input it, so it can perform the calculations.

Have fun testing and thanks for reading me! ;~}
