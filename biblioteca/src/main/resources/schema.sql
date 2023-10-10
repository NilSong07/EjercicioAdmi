/*----------------------------------------------------------------------------------------
Tabla         : prestamos
Base de Datos : biblioteca
Autor        : William Pedraza - Cromasoft
Fecha        : 09/10/2023
----------------------------------------------------------------------------------------*/

-----------------------------------------------------------------------------
-- DDL for Table 'portal.dbo.pat_tramites'
-----------------------------------------------------------------------------

IF NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = 'public' AND table_name = 'prestamos')
BEGIN
    EXEC('
        CREATE TABLE public.prestamos (
            id SERIAL PRIMARY KEY,
            isbn VARCHAR(10) NOT NULL,
            identificacion_usuario NUMERIC(10) NOT NULL,
            tipo_usuario NUMERIC(1) NOT NULL,
            fecha_devolucion DATE, 
            fecha_insercion DATETIME, 
            usuario_insercion VARCHAR(70) 
        )
    ');
END

