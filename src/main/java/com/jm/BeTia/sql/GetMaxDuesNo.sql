CREATE OR REPLACE FUNCTION GetMaxDuesNo(identificador VARCHAR)
RETURNS integer
AS
$$

DECLARE
sku VARCHAR;
   incremento DECIMAL;
   maxDuesNo NUMERIC;
   remaining_amount NUMERIC;
BEGIN

SELECT pr.sku, pr.price, calcular_precio_con_incremento(pr.sku) AS precio_incrementado
INTO sku, incremento
FROM product as pr where pr.sku = $1;

-- Obtener el máximo número de cuotas
CASE
        WHEN incremento >= 1000 THEN
            maxDuesNo := 30;
WHEN incremento >= 300 THEN
            maxDuesNo := 24;
WHEN incremento >= 250 THEN
            maxDuesNo := 18;
ELSE
            -- Obtener el máximo número de cuotas posible
            remaining_amount := incremento;
FOR i IN 3..12 LOOP
                IF remaining_amount / i > 10 THEN
                    maxDuesNo := i;
                    EXIT; -- Salir del bucle cuando se cumpla la condición
END IF;
END LOOP;
END CASE;

INSERT into product_price (max_dues_no, incremented_price, sku)
VALUES (maxDuesNo, incremento, sku);

-- Devolver el máximo número de cuotas
RETURN maxDuesNo;

END;
$$
LANGUAGE plpgsql;
