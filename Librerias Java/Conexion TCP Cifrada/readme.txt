Librería que implementa un servidor TCP con recepción y envío cifrado (AES 256) y otra para el cliente. Requieren la librería cifrarMensaje.

El cliente escuchará las respuestas del servidor hasta que éste cierre la conexión.

Métodos  conexionTcpCliente:

[void] envioCifrado ([String] Mensaje a enviar, [String] ip del servidor, [int] puerto del servidor, [String] Clave para encriptar/desencriptar)