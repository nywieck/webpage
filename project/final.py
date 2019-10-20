import random

def main():
    playGame()

def playGame():
    gameCount = 0
    compWin = 0
    playerWin = 0
    keepPlaying = 1
    while keepPlaying == 1:
        nextTurn = coinToss()
        board = {1:0, 2:0, 3:0, 4:0, 5:0, 6:0, 7:0, 8:0, 9:0}
        drawBoard(board)
        turnCount = 1
        while nextTurn == 1 or nextTurn == 2:
            print(f'\n\n[[  T U R N  {turnCount}  ]]\n-------------------')
            if nextTurn == 1:
                nextTurn = compTurn(board)
                turnCount += 1
            elif nextTurn == 2:
                nextTurn = playerTurn(board)
                turnCount += 1
        gameCount += 1
        if nextTurn == -1:
            compWin += 1
            print('\nComputer wins!')
        elif nextTurn == -2:
            playerWin += 1
            print('\nPlayer wins!')
        else:
            print('\nDraw!')
        print(f'\n# of games played: {gameCount}\n# of computer wins: {compWin}\n# of player wins: {playerWin}')
        keepPlaying = int(input('\nPlay again (enter 1 or 2)?\n1: Yes, play again\n2: No, quit'))
    print('\nThanks for playing!\n')
    if compWin > playerWin:
        print(f'Computer wins the match, {compWin} games to {playerWin} games.\n')
    elif playerWin > compWin:
        print(f'Player wins the match, {playerWin} games to {compWin} games.\n')
    else:
        print(f'Draw, {playerWin} games to {compWin} games.\n')

def coinToss():
    print("\n\n\n\n_=[[  TIC - TAC - TOE  ]]=_\n\n-------- -------- --------\n")
    print("So you know I am not cheating, flip a coin to decide who goes first, and I'll guess heads or tails!\n")
    input("Press Enter to continue...")
    guess = random.randrange(0, 2, 1)
    if guess == 0:
        guess = "heads"
    else:
        guess = "tails"
    print(f"\nI guessed {guess}!\n\nWho won and will be going first?\n")
    while True:
        firstTurn = input("Enter 1 or 2 to start game:\n1: Computer\n2: Player\n\n\n")
        try:
            choice = int(firstTurn)
            if choice < 1 or choice > 2:
                print("You have fat fingers - input must be the integer 1 or 2, try again!\n")
                continue
            else:
                return(choice)
        except ValueError:
            print("You have fat fingers - input must be the integer 1 or 2, try again!\n")

def drawBoard(board):
    occupiedList = []
    for i in range(0, 9, 1):
        square = board[i+1]
        if square == -1:
            occupiedList.append('x')
        elif square == 1:
            occupiedList.append('o')
        else:
            occupiedList.append(str(i+1))

    board = [[' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' '],
                [' ', ' ', occupiedList[6], ' ', ' ', '|', ' ', ' ', occupiedList[7], ' ', ' ', '|', ' ', ' ', occupiedList[8], ' ', ' '],
                ['-', '-', '-', '-', '-', '|', '-', '-', '-', '-', '-', '|', '-', '-', '-', '-', '-'],
                [' ', ' ', occupiedList[3], ' ', ' ', '|', ' ', ' ', occupiedList[4], ' ', ' ', '|', ' ', ' ', occupiedList[5], ' ', ' '],
                ['-', '-', '-', '-', '-', '|', '-', '-', '-', '-', '-', '|', '-', '-', '-', '-', '-'],
                [' ', ' ', occupiedList[0], ' ', ' ', '|', ' ', ' ', occupiedList[1], ' ', ' ', '|', ' ', ' ', occupiedList[2], ' ', ' '],
                [' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ']]
    draw = ''
    for i in range(0, len(board), 1):
        for j in range(0, len(board[0]), 1):
            draw += str(board[i][j])
        draw += '\n'
    print(f'\n{draw}')

def endGame(board, player):
    c1 = board[1] + board[4] + board[7]
    c2 = board[2] + board[5] + board[8]
    c3 = board[3] + board[6] + board[9]
    c4 = board[7] + board[8] + board[9]
    c5 = board[4] + board[5] + board[6]
    c6 = board[1] + board[2] + board[3]
    c7 = board[7] + board[5] + board[3]
    c8 = board[1] + board[5] + board[9]
    cList = [c1, c2, c3, c4, c5, c6, c7, c8]
    for i in cList:
        if i == -3:
            return(-2)
        elif i == 3:
            return(-1)
    countOpen = 0
    for i in range(1, 10, 1):
        if board[i] == 0:
            countOpen += 1
    if countOpen != 0:
        if player == 1:
            return(2)
        elif player == 2:
            return(1)
    else:
        return(0)

def playerTurn(board):
    choice = input("Choose your next move (enter the number): ")
    while True:
        choice = int(choice)
        if board[choice] == 0:
            choiceDict = {choice: -1}
            board.update(choiceDict)
            break
        choice = input("You cannot move there, the square is already taken - try again: ")
    drawBoard(board)
    return(endGame(board, 2))

def compTurn(board):
    board = decision(board)
    drawBoard(board)
    return(endGame(board, 1))

def decision(board):
    c1 = board[1] + board[4] + board[7]
    c2 = board[2] + board[5] + board[8]
    c3 = board[3] + board[6] + board[9]
    c4 = board[7] + board[8] + board[9]
    c5 = board[4] + board[5] + board[6]
    c6 = board[1] + board[2] + board[3]
    c7 = board[7] + board[5] + board[3]
    c8 = board[1] + board[5] + board[9]
    l1 = [1, 4, 7]
    l2 = [2, 5, 8]
    l3 = [3, 6, 9]
    l4 = [7, 8, 9]
    l5 = [4, 5, 6]
    l6 = [1, 2, 3]
    l7 = [7, 5, 3]
    l8 = [1, 5, 9]
    cList = [c1, c2, c3, c4, c5, c6, c7, c8]
    lList = [l1, l2, l3, l4, l5, l6, l7, l8]
    choice = 0
    for i in range(0, 8, 1):
        if cList[i] == 2:
            for j in lList[i]:
                if board[j] == 0:
                    choiceDict = {j: 1}
                    board.update(choiceDict)
                    return(board)
    return(board)

main()