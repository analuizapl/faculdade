#ifndef GLWIDGET_H
#define GLWIDGET_H


#include <QGLWidget>
#include <QWidget>
#include<QtOpenGL>
#include <GL/glu.h>
#include "trackball.h"
# include"camera.h"
# include "light.h"
# include"material.h"


class GLWidget : public QGLWidget
{
    Q_OBJECT
public:
    explicit GLWidget(QWidget *parent = 0);
    virtual ~ GLWidget () ;

signals :
void statusBarMessage ( QString ns ) ;

protected:
    void initializeGL();
    void resizeGL(int w, int h);
    void paintGL();
    void mouseMoveEvent ( QMouseEvent * event );
    void mousePressEvent ( QMouseEvent * event );
    void mouseReleaseEvent ( QMouseEvent * event );
    void wheelEvent ( QWheelEvent * event );
    void keyPressEvent ( QKeyEvent * event );

public slots :
 void toggleBackgroundColor( bool toBlack);
 void showFileOpenDialog();
 void animate();

private:
    void readOFFFile ( const QString & fileName );
    void genNormals ();
    void genTexCoordsCylinder ();
    void genTangents ();
    void createVBOs ();
    void destroyVBOs ();
    void createShaders ();
    void destroyShaders ();

    unsigned int numVertices;
    unsigned int numFaces;
    QVector4D *vertices;
    QVector3D *normals;
    QVector2D *texCoords;
    QVector4D *tangents;
    unsigned int *indices;

    QGLBuffer *vboVertices;
    QGLBuffer *vboNormals;
    QGLBuffer *vboTexCoords;
    QGLBuffer *vboTangents;
    QGLBuffer *vboIndices;


QGLShader *vertexShader;
    QGLShader *fragmentShader;
    QGLShaderProgram *shaderProgram;
    unsigned int currentShader;

    int texID[2];

    Camera camera;
     Light light;
     Material material;
     TrackBall trackBall;
     QTimer timer;

     double zoom;

     QMatrix4x4 projectionMatrix;
     QMatrix4x4 modelViewMatrix;

};

#endif // GLWIDGET_H
