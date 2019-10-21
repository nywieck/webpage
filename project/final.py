import random
import time

def main():
    playGame()

def playGame():
    gameTitle()
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
                print('\nHmm analyzing', end=' ', flush=True)
                time.sleep(1)
                print('. ', end=' ', flush=True)  
                time.sleep(1)
                print('. \n', end=' ', flush=True)
                time.sleep(1)
                nextTurn = compTurn(board)
                turnCount += 1
            elif nextTurn == 2:
                nextTurn = playerTurn(board)
                turnCount += 1
        gameCount += 1
        if nextTurn == -1:
            compWin += 1
            computerWinsGame()
        elif nextTurn == -2:
            playerWin += 1
            playerWinsGame()
        else:
            drawMsg()
        print(f'\nTotal games played: {gameCount}\nTotal computer wins: {compWin}\nTotal player wins: {playerWin}')
        while True:
            choice = input('\nPlay again (enter 1 or 2)?\n1: Yes, play again\n2: No, quit\n')
            try:
                keepPlaying = int(choice)
                if keepPlaying < 1 or keepPlaying > 2:
                    print("You have fat fingers - input must be the integer 1 or 2, try again!\n")
                    continue
                else:
                    break
            except ValueError:
                print("You have fat fingers - input must be the integer 1 or 2, try again!\n")
        
    if compWin > playerWin:
        computerWinsMatch()
        print(f'Computer wins ({compWin} games to {playerWin} games)\n')
    elif playerWin > compWin:
        playerWinsMatch()
        print(f'Player wins ({playerWin} games to {compWin} games)\n')
    else:
        drawMsg()
        print(f'Draw, {playerWin} games each.\n')
    print('Thanks for playing!\n')

def coinToss():
    print("\nSo you know I am not cheating, flip a coin to decide who goes first, and I'll guess heads or tails!\n")
    input("Press Enter to continue...")
    guess = random.randrange(0, 2, 1)
    if guess == 0:
        guess = "HEADS"
    else:
        guess = "TAILS"
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
    while True:
        square = input("Choose your next move (enter the number): \n")
        try:
            choice = int(square)
            if choice < 1 or choice > 9:
                print("You have fat fingers - input must be an integer from 1 to 9!\n")
                continue
            elif board[choice] != 0:
                print("You cannot move there, the square is already taken - try again!\n")
                continue
            else:
                choiceDict = {choice: -1}
                board.update(choiceDict)
                break
        except ValueError:
            print("You have fat fingers - input must be an integer from 1 to 9!\n")
    drawBoard(board)
    return(endGame(board, 2))

def compTurn(board):
    board = decision(board)
    drawBoard(board)
    return(endGame(board, 1))

# if go first, can try to setup guaranteed win
# if go second, must prevent impossible loss
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
    for i in range(1, 10, 1):
        if board[i] != 0:
            occupiedSquares += 1

    # 1) EVAL FIRST TURN
    if occupiedSquares == 0:
        strategy = random.randrange(0, 3, 1)
        # ORIGINAL
        if strategy == 1:
            choiceDict = {5:1}
            board.update(choiceDict)
            return(board)
        # NEW CORNER
        elif strategy == 2:
            choiceDict = {7:1}
            board.update(choiceDict)
            return(board)
        # NEW SIDE
        else:
            choiceDict = {4:1}
            board.update(choiceDict)
            return(board)

    # 2) EVAL SECOND TURN
    if occupiedSquares == 1:
        if board[5] == 0:
            choiceDict = {5:1}
            board.update(choiceDict)
            return(board)
        elif board[5] == -1:
            choiceDict = {3:1}
            board.update(choiceDict)
            return(board)

    # 2) ALWAYS EVAL IF CAN WIN THIS TURN
    for i in range(0, 8, 1):
        if winConList[i] == 2:
            for j in winSquaresList[i]:
                if board[j] == 0:
                    choiceDict = {j:1}
                    board.update(choiceDict)
                    return(board)

    # 3) ALWAYS EVAL IF NEED TO PREVENT LOSS THIS TURN
    for i in range(0, 8, 1):
        if winConList[i] == -2:
            for j in winSquaresList[i]:
                if board[j] == 0:
                    choiceDict = {j: 1}
                    board.update(choiceDict)
                    return(board)

    # 4) EVAL THIRD TURN
    # <<<<<<<  if occupiedSquares == 2, then means can be aggressive don't need worry about unwinnable, try to setup own >>>>>>>
    # <<<<<<<  phase 1 is in third turn, phase 2 is in fifth turn if works out, and last move in turn 7 will be auto by above code >>>>>>>
    # A
    if occupiedSquares == 2:
        if board[5] == 1:
            if board[6] == -1:
                choiceDict = {4:1}
                board.update(choiceDict)
                return(board)
            elif board[8] == -1:
                choiceDict = {2:1}
                board.update(choiceDict)
                return(board)
            elif board[4] == -1:
                choiceDict = {6:1}
                board.update(choiceDict)
                return(board)
            elif board[2] == -1:
                choiceDict = {8: 1}
                board.update(choiceDict)
                return(board)
            # B
            elif board[1] == -1:
                choiceDict = {9: 1}
                board.update(choiceDict)
                return(board)
            elif board[3] == -1:
                choiceDict = {7: 1}
                board.update(choiceDict)
                return(board)
            elif board[7] == -1:
                choiceDict = {3: 1}
                board.update(choiceDict)
                return(board)
            elif board[9] == -1:
                choiceDict = {1: 1}
                board.update(choiceDict)
                return(board)

        # C NEW CORNER STRATEGY
        if board[7] == 1:
            # C1 STRATEGY
            if board[5] == -1:
                choiceDict = {3: 1}
                board.update(choiceDict)
                return(board)
            # C2 THIS IS A SUB-CORNER STRATEGY THAT WILL USE THE SAME PATTERN AS CODED BELOW BUT STARTING SQUARE 3
            elif board[5] == 0 and board[3] == -1:
                choiceDict = {5: 1}
                board.update(choiceDict)
                return(board)
            # C3 STRATEGY
            elif board[1] == -1 or board[2] == -1 or board[4] == -1:
                choiceDict = {6: 1}
                board.update(choiceDict)
                return(board)
            elif board[6] == -1 or board[8] == -1 or board[9] == -1:
                choiceDict = {2: 1}
                board.update(choiceDict)
                return(board)

        if board[4] == 1:
            # D STRATEGY
            if board[5] == -1:
                choiceDict = {2: 1}
                board.update(choiceDict)
                return(board)
            if board[6] == -1:
                choiceDict = {5: 1}
                board.update(choiceDict)
                return(board)
            # E STRATEGY
            if board[3] == -1 or board[2] == -1:
                choiceDict = {9: 1}
                board.update(choiceDict)
                return(board)
            # F STRATEGY
            if board[9] == -1 or board[8] == -1:
                choiceDict = {2: 1}
                board.update(choiceDict)
                return(board)

    # <<<<<<<  if occupiedSquares == 2, then means must be careful about unwinnable conditions, must prevent now   >>>>>>>
    # 5) EVAL FOURTH TURN - PREVENT guaranteed loss in next TWO turns
    if occupiedSquares == 3:
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

        prevent2 = [[7, 5, 6], [1, 5, 6], [1, 5, 8], [3, 5, 8], [4, 5, 9], [4, 5, 3], [2, 5, 7], [2, 5, 9]]
        if (board[prevent2[0][0]] == -1 and board[prevent2[0][1]] == 1 and board[prevent2[0][2]] == -1) or \
           (board[prevent2[3][0]] == -1 and board[prevent2[3][1]] == 1 and board[prevent2[3][2]] == -1):
                choiceDict = {9:1}
                board.update(choiceDict)
                return(board)
        elif (board[prevent2[5][0]] == -1 and board[prevent2[5][1]] == 1 and board[prevent2[5][2]] == -1) or \
         (board[prevent2[6][0]] == -1 and board[prevent2[6][1]] == 1 and board[prevent2[6][2]] == -1):
                choiceDict = {1:1}
                board.update(choiceDict)
                return(board)
        elif (board[prevent2[2][0]] == -1 and board[prevent2[2][1]] == 1 and board[prevent2[2][2]] == -1) or \
             (board[prevent2[4][0]] == -1 and board[prevent2[4][1]] == 1 and board[prevent2[4][2]] == -1):
                choiceDict = {7:1}
                board.update(choiceDict)
                return(board)
        elif (board[prevent2[1][0]] == -1 and board[prevent2[1][1]] == 1 and board[prevent2[1][2]] == -1) or \
             (board[prevent2[7][0]] == -1 and board[prevent2[7][1]] == 1 and board[prevent2[7][2]] == -1):
                choiceDict = {3:1}
                board.update(choiceDict)
                return(board)

        prevent3 = [[5, 6, 8], [5, 2, 4], [5, 4, 8], [5 ,2, 6]]
        if (board[prevent3[0][0]] == 1 and board[prevent3[0][1]] == -1 and board[prevent3[0][2]] == -1):
            choiceDict = {9:1}
            board.update(choiceDict)
            return(board)
        elif (board[prevent3[1][0]] == 1 and board[prevent3[1][1]] == -1 and board[prevent3[1][2]] == -1):
            choiceDict = {1:1}
            board.update(choiceDict)
            return(board)
        elif (board[prevent3[2][0]] == 1 and board[prevent3[2][1]] == -1 and board[prevent3[2][2]] == -1):
            choiceDict = {7:1}
            board.update(choiceDict)
            return(board)
        elif (board[prevent3[3][0]] == 1 and board[prevent3[3][1]] == -1 and board[prevent3[3][2]] == -1):
            choiceDict = {3:1}
            board.update(choiceDict)
            return(board)

    # 6) FIFTH TURN - COMPLETE setting up guaranteed win if went first here
    # A
    if occupiedSquares == 4:
        if board[5] == 1:
            if board[4] == 1 and board[6] == -1 and board[8] == -1:
                choiceDict = {1:1}
                board.update(choiceDict)
                return(board)
            elif board[4] == 1 and board[2] == -1 and board[6] == -1:
                choiceDict = {7:1}
                board.update(choiceDict)
                return(board)
            elif board[2] == 1 and board[6] == -1 and board[8] == -1:
                choiceDict = {1:1}
                board.update(choiceDict)
                return(board)
            elif board[2] == 1 and board[4] == -1 and board[8] == -1:
                choiceDict = {3:1}
                board.update(choiceDict)
                return(board)
            elif board[6] == 1 and board[2] == -1 and board[4] == -1:
                choiceDict = {9:1}
                board.update(choiceDict)
                return(board)
            elif board[6] == 1 and board[4] == -1 and board[8] == -1:
                choiceDict = {3:1}
                board.update(choiceDict)
                return(board)
            elif board[8] == 1 and board[2] == -1 and board[4] == -1:
                choiceDict = {9:1}
                board.update(choiceDict)
                return(board)
            elif board[8] == 1 and board[2] == -1 and board[6] == -1:
                choiceDict = {7:1}
                board.update(choiceDict)
                return(board)
            # B 
            elif (board[1] == -1 and board[8] == -1 and board[6] == 1) or (board[3] == -1 and board[4] == -1 and board[7] == 1):
                choiceDict = {8:1}
                board.update(choiceDict)
                return(board)
            elif (board[9] == -1 and board[4] == -1 and board[1] == 1) or (board[7] == -1 and board[6] == -1 and board[3] == 1):
                choiceDict = {2:1}
                board.update(choiceDict)
                return(board)
            elif (board[9] == -1 and board[1] == -1 and board[1] == 1) or (board[3] == -1 and board[8] == -1 and board[7] == 1):
                choiceDict = {4:1}
                board.update(choiceDict)
                return(board)
            elif (board[1] == -1 and board[8] == -1 and board[9] == 1) or (board[7] == -1 and board[2] == -1 and board[3] == 1):
                choiceDict = {6:1}
                board.update(choiceDict)
                return(board)

        # C NEW CORNER STRATEGY
        if board[7] == 1:
            # C1 STRATEGY
            if board[5] == -1:
                if board[1] == -1:
                    choiceDict = {9:1}
                    board.update(choiceDict)
                    return(board)
                elif board[9] == -1:
                    choiceDict = {1:1}
                    board.update(choiceDict)
                    return(board)
                # C3 STRATEGY
                elif board[6] == 1 and board[4] == -1:
                    choiceDict = {9:1}
                    board.update(choiceDict)
                    return(board)
                elif board[2] == 1 and board[8] == -1:
                    choiceDict = {1:1}
                    board.update(choiceDict)
                    return(board)

            # C2 STRATEGY
            elif board[5] == 1:
                if board[8] == -1:
                    choiceDict = {1:1}
                    board.update(choiceDict)
                    return(board)
                elif board[4] == -1:
                    choiceDict = {9:1}
                    board.update(choiceDict)
                    return(board)

        # D AND E AND F STRATEGIES
        if board[4] == 1:
            if board[9] == 1:
                if board[3] == -1:
                    if board[6] == -1:
                        choiceDict = {7:1}
                        board.update(choiceDict)
                        return(board)
                    elif board[8] == -1:
                        choiceDict = {5:1}
                        board.update(choiceDict)
                        return(board)
                elif board[2] == -1:
                    if board[6] == -1:
                        choiceDict = {7:1}
                        board.update(choiceDict)
                        return(board)
                    elif board[7] == -1:
                        choiceDict = {5:1}
                        board.update(choiceDict)
                        return(board)
            elif board[2] == 1:
                if board[6] == -1 or board[8] == -1:
                    choiceDict = {1:1}
                    board.update(choiceDict)
                    return(board)
            elif board[3] == 1:
                if board[9] == -1:
                    if board[6] == -1:
                        choiceDict = {1:1}
                        board.update(choiceDict)
                        return(board)
                    elif board[2] == -1:
                        choiceDict = {5:1}
                        board.update(choiceDict)
                        return(board)
                if board[8] == -1:
                    if board[1] == -1:
                        choiceDict = {5:1}
                        board.update(choiceDict)
                        return(board)
                    elif board[6] == -1:
                        choiceDict = {1:1}
                        board.update(choiceDict)
                        return(board)
            
    # <<<<<<< WHERE TO GO IF NONE OF THE ABOVE, NOT DETERMINED BY THE OCCUPIED SQUARES TURN COUNTER >>>>>>>
    # 7) EVAL where to go if all other above conditions not met
    for i in range(1, 10):
        if board[i] == 0:
            choiceDict = {i:1}
            board.update(choiceDict)
            return(board)

def gameTitle():
    print("""\n
  _   _        _               _             
 | | (_)      | |             | |            
 | |_ _  ___  | |_ __ _  ___  | |_ ___   ___ 
 | __| |/ __| | __/ _` |/ __| | __/ _ \ / _ \\
 | |_| | (__  | || (_| | (__  | || (_) |  __/
  \__|_|\___|  \__\__,_|\___|  \__\___/ \___|\n\n""")

def computerWinsGame():
    print("""\n\n
                                  _                       _           
                                 | |                     (_)          
   ___ ___  _ __ ___  _ __  _   _| |_ ___ _ __  __      ___ _ __  ___ 
  / __/ _ \| '_ ` _ \| '_ \| | | | __/ _ \ '__| \ \ /\ / / | '_ \/ __|
 | (_| (_) | | | | | | |_) | |_| | ||  __/ |     \ V  V /| | | | \__ \\
  \___\___/|_| |_| |_| .__/ \__,_|\__\___|_|      \_/\_/ |_|_| |_|___/
                     | |                                              
                     |_|                                              
    \n\n""")

def playerWinsGame():
    print("""\n\n
        _                                  _           
       | |                                (_)          
  _ __ | | __ _ _   _  ___ _ __  __      ___ _ __  ___ 
 | '_ \| |/ _` | | | |/ _ \ '__| \ \ /\ / / | '_ \/ __|
 | |_) | | (_| | |_| |  __/ |     \ V  V /| | | | \__ \\
 | .__/|_|\__,_|\__, |\___|_|      \_/\_/ |_|_| |_|___/
 | |             __/ |                                 
 |_|            |___/                                  
    \n\n""")

def drawMsg():
    print("""\n\n
      _                    
     | |                   
   __| |_ __ __ ___      __
  / _` | '__/ _` \ \ /\ / /
 | (_| | | | (_| |\ V  V / 
  \__,_|_|  \__,_| \_/\_/  
    \n\n""")

def computerWinsMatch():
    print("""
                                  _                       _                             _       _     
                                 | |                     (_)                           | |     | |    
   ___ ___  _ __ ___  _ __  _   _| |_ ___ _ __  __      ___ _ __  ___   _ __ ___   __ _| |_ ___| |__  
  / __/ _ \| '_ ` _ \| '_ \| | | | __/ _ \ '__| \ \ /\ / / | '_ \/ __| | '_ ` _ \ / _` | __/ __| '_ \ 
 | (_| (_) | | | | | | |_) | |_| | ||  __/ |     \ V  V /| | | | \__ \ | | | | | | (_| | || (__| | | |
  \___\___/|_| |_| |_| .__/ \__,_|\__\___|_|      \_/\_/ |_|_| |_|___/ |_| |_| |_|\__,_|\__\___|_| |_|
                     | |                                                                              
                     |_|                                                                              
    \n""")

def playerWinsMatch():
    print("""
        _                                  _                             _       _     
       | |                                (_)                           | |     | |    
  _ __ | | __ _ _   _  ___ _ __  __      ___ _ __  ___   _ __ ___   __ _| |_ ___| |__  
 | '_ \| |/ _` | | | |/ _ \ '__| \ \ /\ / / | '_ \/ __| | '_ ` _ \ / _` | __/ __| '_ \ 
 | |_) | | (_| | |_| |  __/ |     \ V  V /| | | | \__ \ | | | | | | (_| | || (__| | | |
 | .__/|_|\__,_|\__, |\___|_|      \_/\_/ |_|_| |_|___/ |_| |_| |_|\__,_|\__\___|_| |_|
 | |             __/ |                                                                 
 |_|            |___/                                                                  
    \n""")

main()