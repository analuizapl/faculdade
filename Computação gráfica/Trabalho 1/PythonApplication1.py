from tkinter import *
from math import cos, sin, sqrt, pow

app = Tk() 
app.geometry('1000x600')
app.title("Implementação algoritmos")
canvas = Canvas(bg="white", width=600, height=400)
canvas.pack()


# Pega coordenadas do mouse pela primeira vez (botão esquerdo)
def click1(event):
    x = event.x
    y = event.y
    x1_value.set(x)
    y1_value.set(y)

# Pega coordenadas do mouse pela segunda vez (botão direito)
def click2(event2):
    x1 = event2.x
    y1 = event2.y
    x2_value.set(x1)
    y2_value.set(y1)

# Limpa a tela
def clear_canvas():
        canvas.delete(ALL)

        x1_value.set("")
        y1_value.set("")
        x2_value.set("")
        y2_value.set("")

def clear_canvas2():
        canvas.delete(ALL)
x1_value=0
y1_value=0
x2_value=0
y2_value=0


#---------------------- RASTERIZAÇÃO--------------------------------------#
# ALGORITMO DDA
def DDA():
     if int(x1_value.get()) == 0 and int(x2_value.get())== 0 and int(y1_value.get()) == 0 and int(y2_value.get())== 0:
        print("insira os numeros")
     else:
            x1 = int(x1_value.get())
            y1 = int(y1_value.get())
            x2 = int(x2_value.get())
            y2 = int(y2_value.get())

            dx = x2 - x1
            dy = y2 - y1

            if abs(dx) > abs(dy):
                steps = abs(dx)
            else:
                steps = abs(dy)

            i=1
            x_increment = dx/steps
            y_increment = dy/steps
            for i in range(steps):
                x1 = x1 + x_increment
                y1 = y1 + y_increment
                canvas.create_line(x1,y1,x1+1,y1+1)


#BRESENHAM
def Bresenham():
        
        x1 = int(x1_value.get())
        y1 = int(y1_value.get())
        x2 = int(x2_value.get())
        y2 = int(y2_value.get())
        tipo=2

        dx = x2 - x1
        dy = y2 - y1

        if dx >= 0:
            x_increment = 1
        else:
            x_increment = -1
            dx = dx * -1
           

        if dy >= 0:
            y_increment = 1
        else:
            y_increment = -1
            dy = dy * -1
        x=x1;
        y=y1;

        if dy < dx:
            p = 2*dy - dx
            const1 = 2*dy
            const2 = 2*(dy-dx)

            for i in range(dx):
                x = x + x_increment
                if p < 0:
                    p = p + const1
                else:
                    y = y + y_increment
                    p = p + const2
                    
                canvas.create_line(x,y,x+1,y+1)

        else:
            p = 2*dx - dy
            const1 = 2*dx
            const2 = 2*(dx-dy)

            for i in range(dx):
                y = y + y_increment
                if p < 0:
                    p = p + const1
                else:
                    x = x + x_increment
                    p = p + const2
 

                canvas.create_line(x,y,x+1,y+1)

#BRESENHAM P/ CIRCUNFERENCIA
def Bresenham_Circle():
        xc = int(x1_value.get())
        yc = int(y1_value.get())
        x = int(x2_value.get())
        y = int(y2_value.get())
        tipo=3

        r = sqrt(pow((x - xc), 2) + pow((y - yc), 2))

        x = 0
        y = r
        p = 3-2*r
        plot_circle_points(x, y, xc, yc)

        while x < y:
            if p < 0:
                p=p+ 4 * x + 6
            else:
                p =p+4 * (x - y) + 10
                y = y - 1
            x = x + 1
            plot_circle_points(x, y, xc, yc)

def plot_circle_points(x, y, xc, yc):

        canvas.create_line((xc + x), (yc + y), (xc + x)+1, (yc + y)+1)
        canvas.create_line((xc - x), (yc + y), (xc - x)+1, (yc + y)+1)
        canvas.create_line((xc + x), (yc - y), (xc + x)+1, (yc - y)+1)
        canvas.create_line((xc - x), (yc - y), (xc - x)+1, (yc - y)+1)
        canvas.create_line((xc + y), (yc + x), (xc + y)+1, (yc + x)+1)
        canvas.create_line((xc - y), (yc + x), (xc - y)+1, (yc + x)+1)
        canvas.create_line((xc + y), (yc - x), (xc + y)+1, (yc - x)+1)
        canvas.create_line((xc - y), (yc - x), (xc - y)+1, (yc - x)+1)

#------------------------TRANSFORMAÇOES 2D----------------------------------#

# Desenha um retangulo de acordo com coordenadas
def rectangle():
  x = int(x1_value.get())
  y = int(y1_value.get())
  x2 = int(x2_value.get())
  y2 = int(y2_value.get())
  tipo=4
  canvas.create_rectangle(x, y, x2, y2, outline="#000000")

# ALGORITMO DE TRANSLACAO
def translation():
        #rectangle();
        x1 = int(x1_value.get())
        y1 = int(y1_value.get())
        x2 = int(x2_value.get())
        y2 = int(y2_value.get())
        trans_value = int(trans.get())

        new_x1 = x1 + trans_value
        new_y1 = y1 + trans_value
        new_x2 = x2 + trans_value
        new_y2 = y2 + trans_value

        x1_value.set(str(new_x1))
        y1_value.set(str(new_y1))
        x2_value.set(str(new_x2))
        y2_value.set(str(new_y2))
        clear_canvas2()
        if (var.get() ==1):
            rectangle()
        if (var.get() == 2):
            clear_canvas2()
            DDA()
        if (var.get() == 3):
            clear_canvas2()
            Bresenham_Circle()

# ALGORITMO DE ROTACAO
def rotate():
        x1 = int(x1_value.get())
        y1 = int(y1_value.get())
        x2 = int(x2_value.get())
        y2 = int(y2_value.get())
        rot_value = int(rot.get())

        rt = rot_value

        new_x1 = int((x1 * cos(rt)) - (y1) * sin(rt))
        new_y1 = int((x2 * sin(rt)) - (y2) * cos(rt))

        x1_value.set(str(new_x1))
        y1_value.set(str(new_y1))
        clear_canvas2()
        if (var.get() ==1):
            rectangle()
        if (var.get() ==2):
            clear_canvas2()
            DDA()
        if (var.get() ==3):
            clear_canvas2()
            Bresenham_Circle()

# ALGORITMO DE ESCALA
def scale():        #????
        x1 = int(x1_value.get())
        y1 = int(y1_value.get())
        x2 = int(x2_value.get())
        y2 = int(y2_value.get())
        esc_value = int(esc.get())

        sc = esc_value

        new_x1 = x1 * sc
        new_y1 = y1 * sc
        new_x2 = x2 * sc
        new_y2 = y2 * sc

        x1_value.set(str(new_x1))
        y1_value.set(str(new_y1))
        x2_value.set(str(new_x2))
        y2_value.set(str(new_y2))
        clear_canvas2()
        if (var.get() ==1):
            rectangle()
        if (var.get() ==2):
            clear_canvas2()
            DDA()
        if (var.get() ==3):
            clear_canvas2()
            Bresenham_Circle()

# ALGORITMOS DE REFLEXÃO
def reflectionY():
        x1 = int(x1_value.get())
        y1 = int(y1_value.get())
        x2 = int(x2_value.get())
        y2 = int(y2_value.get())

        new_x1 = x1 +180
        new_y1 = y1 
        new_x2 = x2 +180
        new_y2 = y2 

        x1_value.set(str(new_x1))
        y1_value.set(str(new_y1))
        x2_value.set(str(new_x2))
        y2_value.set(str(new_y2))

        clear_canvas2()

        if (var.get() ==1):
            rectangle()
        if (var.get() ==2):
            clear_canvas2()
            DDA()
        if (var.get() ==3):
            clear_canvas2()
            Bresenham_Circle()

def reflectionX():
        x1 = int(x1_value.get())
        y1 = int(y1_value.get())
        x2 = int(x2_value.get())
        y2 = int(y2_value.get())
        
        new_x1 = x1
        new_y1 = y1+180 
        new_x2 = x2
        new_y2 = y2 +180

        x1_value.set(str(new_x1))
        y1_value.set(str(new_y1))
        x2_value.set(str(new_x2))
        y2_value.set(str(new_y2))

        clear_canvas2()

        if (var.get() ==1):
            rectangle()
        if (var.get() ==2):
            clear_canvas2()
            DDA()
        if (var.get() ==3):
            clear_canvas2()
            Bresenham_Circle()

def reflectionXY():
        x1 = int(x1_value.get())
        y1 = int(y1_value.get())
        x2 = int(x2_value.get())
        y2 = int(y2_value.get())
       
        new_x1 = x1 +180
        new_y1 = y1 +180
        new_x2 = x2 +180
        new_y2 = y2 +180

        x1_value.set(str(new_x1))
        y1_value.set(str(new_y1))
        x2_value.set(str(new_x2))
        y2_value.set(str(new_y2))

        clear_canvas2()

        if (var.get() ==1):
            rectangle()
        if (var.get() ==2):
            clear_canvas2()
            DDA()
        if (var.get() ==3):
            clear_canvas2()
            Bresenham_Circle()

#------------------------RECORTES----------------------------------#

INSIDE = 0  # 0000 
LEFT = 1    # 0001 
RIGHT = 2   # 0010 
BOTTOM = 4  # 0100 
TOP = 8     # 1000 

x_max = 350
y_max = 350
x_min = 50
y_min = 50

def computeCode(x, y): 
    code = INSIDE 
    if x < x_min:      # to the left of rectangle 
        code |= LEFT 
    elif x > x_max:    # to the right of rectangle 
        code |= RIGHT 
    if y < y_min:      # below the rectangle 
        code |= BOTTOM 
    elif y > y_max:    # above the rectangle 
        code |= TOP 

    return code

def CohenSutherland():
    x1 = int(x1_value.get())
    y1 = int(y1_value.get())
    x2 = int(x2_value.get())
    y2 = int(y2_value.get())

    canvas.create_line(x1, y1, x2, y2, fill = 'pink')



    code1 = computeCode(x1, y1)
    code2 = computeCode(x2, y2)
    accept = False

    while True: 
  
        if code1 == 0 and code2 == 0: 
            accept = True
            break
  
        elif (code1 & code2) != 0: 
            break
  
        else: 
            x = 1.0
            y = 1.0
            if code1 != 0: 
                code_out = code1 
            else: 
                code_out = code2 
  
            if code_out & TOP: 
                
                x = x1 + ((x2 - x1) / (y2 - y1)) * (y_max - y1) 
                y = y_max 
  
            elif code_out & BOTTOM: 
                x = x1 + ((x2 - x1) / (y2 - y1)) * (y_min - y1) 
                y = y_min 
  
            elif code_out & RIGHT: 
                 
                y = y1 + ((y2 - y1) / (x2 - x1)) * (x_max - x1) 
                x = x_max 
  
            elif code_out & LEFT: 
                  
                y = y1 + ((y2 - y1) / (x2 - x1)) * (x_min - x1)  
                x = x_min 
  

            if code_out == code1: 
                x1 = x 
                y1 = y 
                code1 = computeCode(x1, y1) 
  
            else: 
                x2 = x 
                y2 = y 
                code2 = computeCode(x2, y2) 
  
    if accept: 
        canvas.create_line(x1, y1, x2, y2, fill = 'blue',  width = 1)

def LiangBarsky():
        x1 = int(x1_value.get())
        y1 = int(y1_value.get())
        x2 = int(x2_value.get())
        y2 = int(y2_value.get())
        p = [0]*4;
        q = [0]*4;
        dx = x2 - x1;
        dy = y2 - y1;
        tmin = 0;
        tmax = 1;
        p[0] = -(x2 - x1);
        p[1] = (x2 - x1);
        p[2] = -(y2 - y1);
        p[3] = (y2 - y1);
        q[0] = x1 - x_min;
        q[1] = x_max - x1;
        q[2] = y1 - y_min;
        q[3] = y_max - y1;
        for i in range(0, 4):
            if(p[i] < 0):
                tmin = max(tmin, (q[i]/p[i]));
            elif(p[i] > 0):
                tmax = min(tmax, (q[i]/p[i]));
            elif(q[i] < 0):
                tmax = 0;
                tmin = 1;
                break;
        if (tmin < tmax):
            canvas.create_line(((x1 + tmin * dx), (y1 + tmin * dy), (x1 + tmax * dx), (y1 + tmax * dy)),fill=('pink'))

frame = Frame(app)
frame.pack()


# DEFINIR VALORES
label = Label(frame, text="x1: ").grid(row = 0, column = 2)
label = Label(frame, text="y1: ").grid(row = 0, column = 4)
label = Label(frame, text="x2: ").grid(row = 1, column = 2)
label = Label(frame, text="y2: ").grid(row = 1, column = 4)
label = Label(frame, text="translacao ").grid(row = 0, column = 6)
label = Label(frame, text="rotacao ").grid(row = 1, column = 6)
label = Label(frame, text="escala ").grid(row = 0, column = 8)


x1_value = StringVar()
y1_value = StringVar()
x2_value = StringVar()
y2_value = StringVar()
trans = StringVar()
rot = StringVar()
esc = StringVar()
Entry(frame, textvariable = x1_value).grid(row = 0, column = 3)
Entry(frame, textvariable = y1_value).grid(row = 0, column = 5)
Entry(frame, textvariable = x2_value).grid(row = 1, column = 3)
Entry(frame, textvariable = y2_value).grid(row = 1, column = 5)
Entry(frame, textvariable = trans).grid(row = 0, column = 7)
Entry(frame, textvariable = rot).grid(row = 1, column = 7)
Entry(frame, textvariable = esc).grid(row = 0, column = 9)
 
 # BOTOES
btn = Button(app, text ="DDA", command = DDA)
btn.place(x=10, y=450)

btn = Button(app, text ="BRESENHAM", command = Bresenham)
btn.place(x=10, y=480)

btn = Button(app, text ="CIRCUNFERENCIA", command = Bresenham_Circle)
btn.place(x=10, y=510)

btn = Button(app, text ="TRANSLAÇÃO", command = translation)
btn.place(x=150, y=450)

btn = Button(app, text ="ROTAÇÃO", command = rotate)
btn.place(x=150, y=480)

btn = Button(app, text ="ESCALA", command = scale)
btn.place(x=150, y=510)

btn = Button(app, text ="LIMPAR", command = clear_canvas)
btn.place(x=500, y=450)

btn = Button(app, text ="REFLEXÃO Y", command = reflectionY)
btn.place(x=250, y=450)

btn = Button(app, text ="REFLEXÃO X", command = reflectionX)
btn.place(x=250, y=480)

btn = Button(app, text ="REFLEXÃO X/Y", command = reflectionXY)
btn.place(x=250, y=510)

btn = Button(app, text ="RECORTE CS", command = CohenSutherland)
btn.place(x=350, y=450)

btn = Button(app, text ="RECORTE LB", command = LiangBarsky)
btn.place(x=350, y=480)

var = IntVar()
R1 = Radiobutton(app, text="Retangulo", variable=var, value=1,command=rectangle)
R1.pack()
R1.place(x=10, y=20)
R2 = Radiobutton(app, text="Reta", variable=var, value=2,command=DDA)
R2.pack()
R2.place(x=10, y=40)

R3 = Radiobutton(app, text="Circulo", variable=var, value=3,command=Bresenham_Circle)
R3.pack( )
R3.place(x=10, y=60)

label = Label(app)
label.pack()

canvas.bind('<ButtonPress-1>', click1)
canvas.bind('<ButtonPress-3>', click2)
mainloop()