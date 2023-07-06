# Computação Gráfica

Este repositório contém os projetos e trabalhos realizados na disciplina de Computação Gráfica.

# Trabalhos

**Trabalho 1:**

O código permite que o usuário selecione pontos no canvas e aplique diferentes algoritmos de rasterização e transformações 2D para exibir desenhos geométricos resultantes.
Ele utiliza a biblioteca Tkinter para criar uma interface gráfica e exibir resultados visuais.

- O código cria uma janela de aplicativo com o tamanho de 1000x600 pixels.
- O canvas é uma área onde os desenhos e as formas geométricas serão exibidos.
- Existem funções para lidar com eventos de clique do mouse (botão esquerdo e botão direito) para obter as coordenadas dos pontos selecionados pelo usuário.
- O código implementa os algoritmos de rasterização, como o algoritmo DDA (Digital Differential Analyzer) e o algoritmo de Bresenham para linhas e círculos.
- Também são implementadas transformações 2D, como translação, rotação, escala e reflexão em relação ao eixo Y.

O código utiliza as coordenadas obtidas dos eventos de clique do mouse e os parâmetros definidos pelo usuário para realizar as operações de desenho e transformação no canvas.

**Trabalho 2**

O programa apresentado é uma implementação de uma classe GLWidget que herda de QGLWidget, uma classe do Qt para exibição de gráficos OpenGL. Essa classe é responsável por renderizar uma figura tridimensional usando OpenGL.

Aqui estão as principais funcionalidades do programa:

- O construtor e o destruidor inicializam e liberam os recursos necessários para o OpenGL, como os buffers de vértices, normais, coordenadas de textura, tangentes e índices, além dos programas de shader.
- O método initializeGL() é chamado sempre que o widget precisa iniciar um novo contexto OpenGL. Ele configura o estado OpenGL, carrega as texturas e inicia um timer para animações.
- O método paintGL() é chamado sempre que o widget precisa ser pintado novamente. Ele limpa o buffer de cores e o buffer de profundidade, configura as matrizes de modelo-visão e projeção, define as propriedades de iluminação e renderiza a cena utilizando os VBOs (Vertex Buffer Objects) e shaders.
- O método resizeGL() é chamado sempre que o tamanho do widget é alterado. Ele atualiza a viewport e recalcula a matriz de projeção.
- O método toggleBackgroundColor() altera a cor de fundo do widget entre preto e branco.
- O método showFileOpenDialog() exibe uma janela de diálogo para o usuário selecionar um arquivo OFF. Em seguida, ele lê o arquivo OFF, gera normais, coordenadas de textura e tangentes, cria os VBOs e shaders correspondentes e atualiza a cena.
- O método readOFFFile() lê um arquivo OFF utilizando a biblioteca ifstream e armazena os vértices e índices em arrays.
- O método genNormals() calcula as normais nos vértices da malha.

