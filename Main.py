import os
import subprocess
import shutil
from py4j.java_gateway import JavaGateway, GatewayParameters, launch_gateway
import pygame

BASE = os.path.dirname(os.path.abspath(__file__))

JAVA_SRC = os.path.join(BASE, "java", "src")
JAVA_OUT = os.path.join(BASE, "java", "deploy")
PY4J_JAR = os.path.join(BASE, "lib", "py4j.jar")

# Delete the entire out folder if it exists
if os.path.exists(JAVA_OUT):
    shutil.rmtree(JAVA_OUT)

# Recreate the empty out folder
os.makedirs(JAVA_OUT)

java_files = []

for root, dirs, files in os.walk(JAVA_SRC):
    for file in files:
        if file.endswith(".java"):
            java_files.append(os.path.join(root, file))

subprocess.run([
    "javac",
    "-d", JAVA_OUT,
    "-cp", PY4J_JAR,
    *java_files
], check=True)

port = launch_gateway(classpath=JAVA_OUT)

gateway = JavaGateway(gateway_parameters=GatewayParameters(port=port))

main = gateway.jvm.chess.Main()

def add_piece(win, piece, x, y):
    piece_type = piece.getName()

    if piece_type == "p":
        win.blit(black_pawn_img, (x * 100, y * 100))
    elif piece_type == "b":
        win.blit(black_bishop_img, (x * 100, y * 100))
    elif piece_type == "n":
        win.blit(black_knight_img, (x * 100, y * 100))
    elif piece_type == "r":
        win.blit(black_rook_img, (x * 100, y * 100))
    elif piece_type == "q":
        win.blit(black_queen_img, (x * 100, y * 100))
    elif piece_type == "k":
        win.blit(black_king_img, (x * 100, y * 100))

    elif piece_type == "P":
        win.blit(white_pawn_img, (x * 100, y * 100))
    elif piece_type == "B":
        win.blit(white_bishop_img, (x * 100, y * 100))
    elif piece_type == "N":
        win.blit(white_knight_img, (x * 100, y * 100))
    elif piece_type == "R":
        win.blit(white_rook_img, (x * 100, y * 100))
    elif piece_type == "Q":
        win.blit(white_queen_img, (x * 100, y * 100))
    elif piece_type == "K":
        win.blit(white_king_img, (x * 100, y * 100))

def draw_board(surface):
    """Draws the alternating chess board pattern."""
    for row in range(8):
        for col in range(8):
            # Alternating logic: if row+col is even, it's light. Otherwise, dark.
            if (row + col) % 2 == 0:
                color = (255, 255, 255)
            else:
                color = (0, 0, 0)
            
            # Define square dimensions and location
            # (X coordinate, Y coordinate, width, height)
            rect = (col * 100, row * 100, 100, 100)
            
            # Draw the square onto the specified surface
            pygame.draw.rect(surface, color, rect)

screen = pygame.display.set_mode((800, 800))

black_pawn_img = pygame.image.load("images/BlackPawn.png").convert_alpha()
black_bishop_img = pygame.image.load("images/BlackBishop.png").convert_alpha()
black_knight_img = pygame.image.load("images/BlackKnight.png").convert_alpha()
black_rook_img = pygame.image.load("images/BlackRook.png").convert_alpha()
black_queen_img = pygame.image.load("images/BlackQueen.png").convert_alpha()
black_king_img = pygame.image.load("images/BlackKing.png").convert_alpha()

white_pawn_img = pygame.image.load("images/WhitePawn.png").convert_alpha()
white_bishop_img = pygame.image.load("images/WhiteBishop.png").convert_alpha()
white_knight_img = pygame.image.load("images/WhiteKnight.png").convert_alpha()
white_rook_img = pygame.image.load("images/WhiteRook.png").convert_alpha()
white_queen_img = pygame.image.load("images/WhiteQueen.png").convert_alpha()
white_king_img = pygame.image.load("images/WhiteKing.png").convert_alpha()

while True:

    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            exit()

    main.update()
    board = main.getBoard()

    draw_board(screen)    

    for y in range(8):
        for x in range(8):
            if board.isOccupy(x, y):
                piece = board.getSquare(x, y)
                add_piece(screen, piece, x, y)

    pygame.display.update()
    