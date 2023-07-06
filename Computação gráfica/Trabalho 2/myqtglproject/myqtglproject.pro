#-------------------------------------------------
#
# Project created by QtCreator 2021-04-16T16:56:35
#
#-------------------------------------------------

QT       += core gui opengl

TARGET = myqtglproject
TEMPLATE = app


SOURCES += main.cpp\
        mainwindow.cpp \
    glwidget.cpp \
    trackball.cpp \
    camera.cpp \
    light.cpp \
    material.cpp

HEADERS  += mainwindow.h \
    glwidget.h \
    trackball.h \
    camera.h \
    light.h \
    material.h

FORMS    += mainwindow.ui


RESOURCES += \
    resources.qrc

DISTFILES += \
    vgouraud.glsl \
    fgouraud.glsl \
    vphong.glsl \
    fphong.glsl \
    vtexture.glsl \
    ftexture.glsl \
    vnormal.glsl \
    fnormal.glsl
