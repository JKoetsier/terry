USE [DbName]
GO
CREATE TABLE [db].[TableName](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[IntField] [int] NULL,
	[VarChar250Field] [nvarchar](250) NULL,
	[DecimalField] [decimal](15, 6) NULL,
	[DateTimeOffsetField] [datetimeoffset](7) NULL,
	[VarCharMaxField] [nvarchar](MAX) NULL,
	[DateField] [date] NULL,
	[BitField] [bit] NULL,
	[DecimalField2] [decimal](38, 15) NULL,
 CONSTRAINT [PK_Instrument] PRIMARY KEY NONCLUSTERED
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, FILLFACTOR = 90) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
