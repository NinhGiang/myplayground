# import thư viện pygame
import pygame 
  
# init font chữ pygame
pygame.font.init() 
  
# tạo màn hình game
screen = pygame.display.set_mode((500, 600)) 
  
# tạo title và icon
pygame.display.set_caption("TRÒ CHƠI SUDOKU - THUẬT TOÁN BACKTRACKING") 
#img = pygame.image.load('icon.png') 
#pygame.display.set_icon(img) 
  
x = 0
y = 0
dif = 500 / 9
val = 0
# Dựng bảng sudoku 
grid =[ 
        [7, 8, 0, 4, 0, 0, 1, 2, 0], 
        [6, 0, 0, 0, 7, 5, 0, 0, 9], 
        [0, 0, 0, 6, 0, 1, 0, 7, 8], 
        [0, 0, 7, 0, 4, 0, 2, 6, 0], 
        [0, 0, 1, 0, 5, 0, 9, 3, 0], 
        [9, 0, 4, 0, 6, 0, 0, 0, 5], 
        [0, 7, 0, 3, 0, 0, 0, 1, 2], 
        [1, 2, 0, 0, 0, 7, 4, 0, 0], 
        [0, 4, 9, 2, 0, 6, 0, 0, 7] 
    ] 
  
# Tải font chữ để dùng sau
#...

# Hàm lấy tọa độ của một ô
#...
  
# Hàm highlight viền ô được chọn
def draw_box(): 
    for i in range(2): 
        pygame.draw.line(screen, (255, 0, 0), (x * dif-3, (y + i)*dif), (x * dif + dif + 3, (y + i)*dif), 7) 
        pygame.draw.line(screen, (255, 0, 0), ( (x + i)* dif, y * dif ), ((x + i) * dif, y * dif + dif), 7)    
  
# Hàm vẽ bảng Sudoku        
def draw(): 

    # Draw the lines 
    for i in range (9): 
        for j in range (9): 
            if grid[i][j]!= 0: 

                # Tô màu blue những ô đã có số
                #...

                # Sau khi tô màu thì điền số vào
                #...

    # Vẽ đường viền ngang và dọc cho bảng sudoku       
    for i in range(10): 
        if i % 3 == 0 : 
            thick = 7
        else: 
            thick = 1
        pygame.draw.line(screen, (0, 0, 0), (0, i * dif), (500, i * dif), thick) 
        pygame.draw.line(screen, (0, 0, 0), (i * dif, 0), (i * dif, 500), thick)       
  
# Hàm điền một số nào đó vào ô   
def draw_val(val): 
    #...
  
# Báo lỗi khi điền sai:
def raise_error1():
    text1 = font1.render("DA GIAI SAI BANG SUDOKU!!!", 1, (0, 0, 0)) 
    screen.blit(text1, (20, 570))   
def raise_error2(): 
    #...
  
# Kiểm tra nếu như số điền vào bảng là đúng
def valid(m, i, j, val):
    # Kiểm tra trùng với chính số đang ghi
    #...

    # Kiểm tra trùng số trong hàng và cột
    #...

    # Kiểm tra trùng số trong ô 3x3
    #...

    return True
  
# Giải Sudoku bằng thuật toán Backtracking
def solve(grid, i, j): 

    while grid[i][j]!= 0: 
        if i<8: 
            i+= 1
        elif i == 8 and j < 8: 
            i = 0
            j+= 1
        elif i == 8 and j == 8: 
            return True
    pygame.event.pump()     
    for it in range(1, 10): 
        if valid(grid, i, j, it)== True: 
            grid[i][j]= it 
            global x, y 
            x = i 
            y = j 
            # background màu trắng\ 
            screen.fill((255, 255, 255)) 
            draw() 
            draw_box() 
            pygame.display.update() 
            pygame.time.delay(20) 
            if solve(grid, i, j)== 1: 
                return True
            else: 
                grid[i][j]= 0
            # background màu trắng\ 
            screen.fill((255, 255, 255)) 
          
            draw() 
            draw_box() 
            pygame.display.update() 
            pygame.time.delay(50)     
    return False  
  
# Hiển thị hướng dẫn chơi 
def instruction(): 
    #...
  
# Hiển thị các lựa chọn sau khi giải xong
def result(): 
    #...
# Chuẩn bị cho vòng lặp
run = True
flag1 = 0 # Cờ xác định xem có đang đổi sang ô nào không. Nếu có thì 1, chưa thì 0 
flag2 = 0 # Cờ xác định xem người chơi có nhấn Enter hay không.
rs = 0 # Biến xác định tình trạng kết quả giải (result). Nếu chưa đúng thì 0, đã giải thì 1
error = 0
# Vòng lặp main
while run: 
      
    # To background màu trắng
    screen.fill((255, 255, 255)) 
    # Kiểm tra các sự kiện (event) trong event.get() 
    for event in pygame.event.get(): 
        # Thoát cửa sổ trò chơi khi bấm QUIT
        #...

        # Đi đến ô mà con trỏ nhấp vào để điền số     
        if event.type == pygame.MOUSEBUTTONDOWN: 
            flag1 = 1
            pos = pygame.mouse.get_pos() 
            get_cord(pos) 
        # Thực hiện thao tác khi nhấn phím
        if event.type == pygame.KEYDOWN: 
            if event.key == pygame.K_LEFT: 
                x-= 1
                flag1 = 1
            if event.key == pygame.K_RIGHT: 
                x+= 1
                flag1 = 1
            if event.key == pygame.K_UP: 
                y-= 1
                flag1 = 1
            if event.key == pygame.K_DOWN: 
                y+= 1
                flag1 = 1    
            if event.key == pygame.K_1: 
                val = 1
            if event.key == pygame.K_2: 
                val = 2    
            if event.key == pygame.K_3: 
                val = 3
            if event.key == pygame.K_4: 
                val = 4
            if event.key == pygame.K_5: 
                val = 5
            if event.key == pygame.K_6: 
                val = 6 
            if event.key == pygame.K_7: 
                val = 7
            if event.key == pygame.K_8: 
                val = 8
            if event.key == pygame.K_9: 
                val = 9  
            # Giải board khi nhấn phím Enter
            if event.key == pygame.K_RETURN: 
                flag2 = 1   
            # Clear board nếu nhấn phím R
            if event.key == pygame.K_r: 
                rs = 0
                error = 0
                flag2 = 0
                grid =[ 
                [0, 0, 0, 0, 0, 0, 0, 0, 0], 
                [0, 0, 0, 0, 0, 0, 0, 0, 0], 
                [0, 0, 0, 0, 0, 0, 0, 0, 0], 
                [0, 0, 0, 0, 0, 0, 0, 0, 0], 
                [0, 0, 0, 0, 0, 0, 0, 0, 0], 
                [0, 0, 0, 0, 0, 0, 0, 0, 0], 
                [0, 0, 0, 0, 0, 0, 0, 0, 0], 
                [0, 0, 0, 0, 0, 0, 0, 0, 0], 
                [0, 0, 0, 0, 0, 0, 0, 0, 0] 
                ] 
            # Trả về board mặc định nếu nhấn phím D
            if event.key == pygame.K_d: 
                rs = 0
                error = 0
                flag2 = 0
                grid =[ 
                    [7, 8, 0, 4, 0, 0, 1, 2, 0], 
                    [6, 0, 0, 0, 7, 5, 0, 0, 9], 
                    [0, 0, 0, 6, 0, 1, 0, 7, 8], 
                    [0, 0, 7, 0, 4, 0, 2, 6, 0], 
                    [0, 0, 1, 0, 5, 0, 9, 3, 0], 
                    [9, 0, 4, 0, 6, 0, 0, 0, 5], 
                    [0, 7, 0, 3, 0, 0, 0, 1, 2], 
                    [1, 2, 0, 0, 0, 7, 4, 0, 0], 
                    [0, 4, 9, 2, 0, 6, 0, 0, 7] 
                ] 
    # Nếu phím Enter được nhấn thì giải đề và kết luận:
    if flag2 == 1: 
        if solve(grid, 0, 0) == False: 
            error = 1
        else: 
            rs = 1
        flag2 = 0    
    # Nếu có nhập số nào đó (val khác 0)
    if val != 0:             
        draw_val(val) 
        # Điền số vào ô nếu số đúng, sau đó dựng lại flag1 = 0
        #...

        # Nếu sai thì cảnh báo
        #...

        val = 0    
    # Cảnh báo các loại lỗi
    #...

    # Hiển thị kết quả
    if rs == 1: 
        result()         
    draw()
    # Vẽ lại hightlight khi một ô được chọn
    if flag1 == 1: 
        draw_box()        
    instruction()     
  
    # Update cửa sổ 
    pygame.display.update()   
  
# Quit cửa sổ pygame
pygame.quit()