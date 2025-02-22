Librería que cifra/descifra el mensaje [String] que le pasemos con AES 256.

Métodos de cifrarMensaje:

[void] generarClave() --> genera una clave aleatoria.

[SecretKey] generarClave ([String] texto que sirve para generar la clave de encriptado/desencriptado) --> genera una clave a partir de un texto dado.

[SecretKey] getClave () --> Devuelve un objeto SecretKey con la última clave que se ha generado.

[String] getClaveString() --> Devuelve un String con la última clave que se ha generado.

[String] cifrar ([String] mensaje a cifrar) --> devuelve el mensaje cifrado generando una clave aleatoria.

[String] cifrar ([String] mensaje a cifrar, [SecretKey] clave para ejecutar la encriptación] --> devuelve el mensaje encriptado con la clave de encriptación facilitada.

[String] descifrar ([String] mensaje a descifrar, [SecretKey] clave para el descifrado) --> devuelve el mensaje descifrado usando la clave facilitada.

[String] descifrarKeyString ([String] mensaje a descifrar, [String] mensaje para usarlo como clave de descifrado) --> devuelve el mensaje descifrado usando el segundo parámetro de tipo String para generar la clave de descifrado.