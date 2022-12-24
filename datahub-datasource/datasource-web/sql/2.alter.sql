use datahub;

DELIMITER $$
-- 1表示新增列,2表示修改列,3表示删除列
CREATE PROCEDURE `datahub_alter` (TableName VARCHAR(50),
                                       ColumnName VARCHAR(50),
                                       SqlStr VARCHAR(4000),
                                       CType INT)
BEGIN
    -- 查询列是否存在
    DECLARE ColumnCount INT;
    DECLARE IndexCount INT;
    SET IndexCount=0;
    SET ColumnCount=0;
    SELECT COUNT(*) INTO ColumnCount  FROM INFORMATION_SCHEMA.Columns WHERE table_schema= DATABASE() AND table_name=TableName AND column_name=ColumnName;
    -- 新增列
    IF (CType=1 AND ColumnCount<=0) THEN
        SET SqlStr := CONCAT('ALTER TABLE ',TableName,' ADD COLUMN ',ColumnName,' ',SqlStr);
        -- 修改列
    ELSEIF (CType=2 AND ColumnCount>0)  THEN
        SET SqlStr := CONCAT('ALTER TABLE ',TableName,' MODIFY  ',ColumnName,' ',SqlStr);
        -- 删除列
    ELSEIF (CType=3 AND ColumnCount>0) THEN
        SET SqlStr := CONCAT('ALTER TABLE  ',TableName,' DROP COLUMN  ',ColumnName);
        -- 修改列名
    ELSEIF (CType=4 AND ColumnCount>0) THEN
        SET SqlStr := CONCAT('ALTER TABLE  ',TableName,' CHANGE COLUMN  ',ColumnName, ' ', SqlStr);
    ELSEIF (CType>4) THEN
        SELECT COUNT(*) INTO IndexCount FROM INFORMATION_SCHEMA.statistics WHERE table_schema= DATABASE() AND table_name=TableName AND index_name=ColumnName;
        -- 添加索引
        IF (CType=5 AND IndexCount<=0) THEN
            SET SqlStr := CONCAT('ALTER TABLE  ',TableName,' ADD INDEX  ',ColumnName, ' ', SqlStr);
            -- 修改索引
        ELSEIF (CType=6) THEN
            IF (IndexCount>0) THEN
                SET SqlStr := CONCAT('ALTER TABLE  ',TableName,' DROP INDEX  ',ColumnName, ';ALTER TABLE  ',TableName,' ADD INDEX  ',ColumnName, ' ', SqlStr);
            ELSE
                SET SqlStr := CONCAT('ALTER TABLE  ',TableName,' ADD INDEX  ',ColumnName, ' ', SqlStr);
            END IF;
            -- 删除索引
        ELSEIF (CType=7 AND IndexCount>0) THEN
            SET SqlStr := CONCAT('ALTER TABLE  ',TableName,' DROP INDEX  ',ColumnName);
        ELSE
            SET SqlStr :='';
        END IF;
    ELSE
        SET SqlStr :='';
    END IF;
    -- 执行命令
    IF (SqlStr<>'') THEN
        SET @SQL1 = SqlStr;
        PREPARE stmt1 FROM @SQL1;
        EXECUTE stmt1;
    END IF;
END$$

