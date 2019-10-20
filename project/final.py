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
    winCon1 = board[1] + board[4] + board[7]
    winCon2 = board[2] + board[5] + board[8]
    winCon3 = board[3] + board[6] + board[9]
    winCon4 = board[7] + board[8] + board[9]
    winCon5 = board[4] + board[5] + board[6]
    winCon6 = board[1] + board[2] + board[3]
    winCon7 = board[7] + board[5] + board[3]
    winCon8 = board[1] + board[5] + board[9]
    winConList = [winCon1, winCon2, winCon3, winCon4, winCon5, winCon6, winCon7, winCon8]
    for i in winConList:
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
    winCon1 = board[1] + board[4] + board[7]
    winCon2 = board[2] + board[5] + board[8]
    winCon3 = board[3] + board[6] + board[9]
    winCon4 = board[7] + board[8] + board[9]
    winCon5 = board[4] + board[5] + board[6]
    winCon6 = board[1] + board[2] + board[3]
    winCon7 = board[7] + board[5] + board[3]
    winCon8 = board[1] + board[5] + board[9]
    winSquares1 = [1, 4, 7]
    winSquares2 = [2, 5, 8]
    winSquares3 = [3, 6, 9]
    winSquares4 = [7, 8, 9]
    winSquares5 = [4, 5, 6]
    winSquares6 = [1, 2, 3]
    winSquares7 = [7, 5, 3]
    winSquares8 = [1, 5, 9]
    winConList = [winCon1, winCon2, winCon3, winCon4, winCon5, winCon6, winCon7, winCon8]
    winSquaresList = [winSquares1, winSquares2, winSquares3, winSquares4, winSquares5, winSquares6, winSquares7, winSquares8]
    choice = 0
    occupiedSquares = 0
    # 1) EVAL FIRST TURN, SECOND TURN
    for i in range(1, 10, 1):
        if board[i] != 0:
            occupiedSquares += 1
    if occupiedSquares == 0:
        choiceDict = {5:1}
        board.update(choiceDict)
        return(board)
    elif occupiedSquares == 1:
        if board[5] == 0:
            choiceDict = {5:1}
            board.update(choiceDict)
            return(board)
        elif board[5] == -1:
            choiceDict = {3:1}
            board.update(choiceDict)
            return(board)

    # 2) EVAL IF CAN WIN THIS TURN
    for i in range(0, 8, 1):
        if winConList[i] == 2:
            for j in winSquaresList[i]:
                if board[j] == 0:
                    choiceDict = {j:1}
                    board.update(choiceDict)
                    return(board)

    # 3) EVAL IF NEED TO PREVENT LOSS THIS TURN
    for i in range(0, 8, 1):
        if winConList[i] == -2:
            for j in winSquaresList[i]:
                if board[j] == 0:
                    choiceDict = {j: 1}
                    board.update(choiceDict)
                    return(board)

    # 4) EVAL IF NEED TO PREVENT LOSS NEXT TURN
    prevent1 = [[1, 5, 9], [3, 5, 7], [9, 5, 1], [7, 5, 3]]
    for layout in prevent1:
        if board[layout[0]] == 1 and board[layout[1]] == -1 and board[layout[2]] == -1:
            if board[1] == 0:
                choiceDict = {1:1}
                board.update(choiceDict)
                return(board)
            elif board[1] != 0:
                choiceDict = {3:1}
                board.update(choiceDict)
                return(board)
        elif board[layout[0]] == -1 and board[layout[1]] == 1 and board[layout[2]] == -1:
                choiceDict = {2:1}
                board.update(choiceDict)
                return(board)

    # 5) PREVENT guaranteed loss in next turn turns
    prevent2 = [[7, 5, 6], [1, 5, 6], [1, 5, 8], [3, 5, 8], [4, 5, 9], [4, 5, 3], [2, 5, 7], [2, 5, 9]]
    if (board[prevent2[0][0]] == -1 and board[prevent2[0][1]] == 1 and board[prevent2[0][2]] == -1) or \
       (board[prevent2[3][0]] == -1 and board[prevent2[3][1]] == 1 and board[prevent2[3][2]] == -1):
       choiceDict = {1:1}
       board.update(choiceDict)
       return(board)
    elif (board[prevent2[2][0]] == -1 and board[prevent2[2][1]] == 1 and board[prevent2[2][2]] == -1) or \
         (board[prevent2[4][0]] == -1 and board[prevent2[4][1]] == 1 and board[prevent2[4][2]] == -1):
         choiceDict = {3:1}
         board.update(choiceDict)
         return(board)
    elif (board[prevent2[1][0]] == -1 and board[prevent2[1][1]] == 1 and board[prevent2[1][2]] == -1) or \
         (board[prevent2[7][0]] == -1 and board[prevent2[7][1]] == 1 and board[prevent2[7][2]] == -1):
         choiceDict = {7:1}
         board.update(choiceDict)
         return(board)
    elif (board[prevent2[5][0]] == -1 and board[prevent2[5][1]] == 1 and board[prevent2[5][2]] == -1) or \
         (board[prevent2[6][0]] == -1 and board[prevent2[6][1]] == 1 and board[prevent2[6][2]] == -1):
         choiceDict = {9:1}
         board.update(choiceDict)
         return(board)

    # 6) EVAL if computer can setup guaranteed win 2 turns later
    

    # 7) EVAL where to go if all other above conditions not met
    for i in range(1, 10):
        if board[i] == 0:
            choiceDict = {i:1}
            board.update(choiceDict)
            return(board)

main()