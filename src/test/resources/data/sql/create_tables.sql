CREATE TABLE db.TableName (
	Id              INT IDENTITY (1, 1) NOT NULL,
	IntField        INT                 NULL,
	VarChar250Field NVARCHAR(250)       NULL,
	DecimalField    DECIMAL(15, 6)      NULL,
	CONSTRAINT PK_Instrument PRIMARY KEY (Id)
);

CREATE TABLE db.Table2Name(
	DateTimeOffsetField datetimeoffset(7) NULL,
	VarCharMaxField nvarchar(MAX) NULL,
	DateField date NULL,
	BitField bit NULL,
	DecimalField2 decimal(38, 15) NULL,
 CONSTRAINT PK_Instrument PRIMARY KEY (BitField)
);
