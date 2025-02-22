Este componente hereda de JLabel y permite el drag and drop, cambiar el tamaño en horizontal y gestiona las colisiones entre ellos.

Constructor de la clase JLabelArrastrable:

JLabelArrastrable ([String] texto que mostrará el Jlabel, [Color] color que queremos que use de fondo, [int] posición del Jlabel dentro del array para gestionar las colisiones siendo máx. 127, [JPanel] Panel donde añadiremos el Jlabel, [JFrame] ventana donde añadiremos el Jpanel al que hemos añadido el Jlabel).

Métodos:

[void] CambiarTamano ([int] nuevo tamaño horizontal, [int] nuevo tamaño vertical] [int] posición en el array, [JPanel] JPanel en el que está añadido para actualizarlo) --> cambia el tamaño del JLabel.

[void] CambiarPosicion ([int] nueva posición horizontal, [int] nueva posicion vertical] [int] posición en el array, [JPanel] JPanel en el que está añadido para actualizarlo) --> cambia la posición del JLabel.

[void] Colision ([int] posición en el array, [JPanel] JPanel en el que está añadido para actualizarlo) --> comprueba si hay colisión del objeto actual con el objeto que se encuentra en la posición del array pasada al método. Si existe desplaza el Jlabel hasta una posición contigua al objeto con el que está colisionando.

[void] modificarArray ([int] posición dentro del array) --> actualiza la información de tamaño y posición del objeto que se encuentra en la posición del array que le pasamos al método.

[void] traerAlFrente ([Jpanel] Panel en que tenemos añadido el objeto) --> trae al frente el objeto.

[void] traerAtras ([int] posición dentro del array, [Jpanel] Panel en que tenemos añadido el objeto) --> mueve hacia atrás el objeto que se encuentra en la posición del array que le pasamos al método.

[void] AjustarAlGrid ([int] posición dentro del array, [Jpanel] Panel en que tenemos añadido el objeto) --> ajusta la posición del objeto a la posición más cercana de la rejilla. En horizontal se establecen 10 pixeles y en vertical 45 pixeles.

