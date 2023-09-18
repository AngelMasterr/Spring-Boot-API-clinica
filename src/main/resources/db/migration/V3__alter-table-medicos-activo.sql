ALTER TABLE `vollmed_api`.`medicos` 
ADD COLUMN `activo` tinyint;
UPDATE MEDICOS SET ACTIVO = 1;