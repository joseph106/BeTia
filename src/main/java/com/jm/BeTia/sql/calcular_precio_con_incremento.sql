CREATE OR REPLACE FUNCTION calcular_precio_con_incremento(sku text)
RETURNS numeric(10, 2) AS
$$
DECLARE
pvp numeric(10, 2);
    incremento numeric(5, 2);
    precio_con_incremento numeric(10, 2);
BEGIN
    -- Obtener el PVP e Incremento del SKU proporcionado
SELECT pr.price, pr."increment" INTO pvp, incremento
FROM product as pr
WHERE pr.sku = $1;

-- Verificar si el PVP es menor a $15.00
IF pvp < 15.00 THEN
        precio_con_incremento := pvp; -- No se aplica el incremento
ELSE
        -- Aplicar el incremento porcentual
        precio_con_incremento := pvp * (1 + (incremento / 100));
END IF;

    -- Redondear según las reglas especificadas
    IF (precio_con_incremento - trunc(precio_con_incremento)) < 0.50 THEN
        precio_con_incremento := trunc(precio_con_incremento)- 0.01;
ELSE
        precio_con_incremento := ceil(precio_con_incremento) - 0.01;
END IF;

RETURN precio_con_incremento;
END;
$$
LANGUAGE plpgsql;
