"""

import random
import other libraries?

def main():
    overall control function calls to all other pieces

def start():
    get input if program needs to guess heads or tails, or if user will
    if computer then use random number to guess heads or tails show output
    get input about who won
    then.... pass results back to main? computer or user starts.

def drawBoard():
    if make own function to do this, then will have to call after each turn and pass updated board positions... good or bad?
    MAYBE DON'T WANT THIS OUTSIDE OF THE GAMEPLAY FUNCTION, BECAUSE CALLS WILL BE MORE COMPLICATED

def gamePlay():
    how to implement taking turns and keeping track of whose turn
        CALL MYTURN THEIR_TURN fuctions within loop
    on user's turn need to get input, then update board state according to input
    on computer's turn need to analyze most updated board state, maybe call decision function here
    while MAIN WHILE LOOP will call the decision function

def my_turn():

def their_turn():

def decision():
    before making decision
        1) check if can win
            how to implement this decision in code
        2) check to ensure don't lose this turn
            how to implement this decision in code
        3) check to ensure not a situation to lose in 2 turns
            how to implement this decision in code
        4) if none of the above... try to setup 2 turn win
        5) if not 5, then maybe go in square with greatest degrees of freedom?
            how to implement this decision in code

        *if go second, turn 1, if middle is open then go in middle, otherwise must go in a corner to prevent potential losing situation

    FINALLY make a decision, update the board, and pass the turn back to user, to get input again.
        where do i put all this into a loop?

    What to do to end the game, keep track of score, and reset for next game?

main()

"""

