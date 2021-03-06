USE [master]
GO
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'Turnos')
BEGIN
CREATE DATABASE [Turnos] ON  PRIMARY 
( NAME = N'Turnos', FILENAME = N'c:\Archivos de programa\Microsoft SQL Server\MSSQL.1\MSSQL\DATA\Turnos.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Turnos_log', FILENAME = N'c:\Archivos de programa\Microsoft SQL Server\MSSQL.1\MSSQL\DATA\Turnos_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
END

GO
ALTER DATABASE [Turnos] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Turnos] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Turnos] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Turnos] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Turnos] SET ARITHABORT OFF 
GO
ALTER DATABASE [Turnos] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Turnos] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [Turnos] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Turnos] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Turnos] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Turnos] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Turnos] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Turnos] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Turnos] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Turnos] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Turnos] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Turnos] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Turnos] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Turnos] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Turnos] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Turnos] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Turnos] SET  READ_WRITE 
GO
ALTER DATABASE [Turnos] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Turnos] SET  MULTI_USER 
GO
ALTER DATABASE [Turnos] SET PAGE_VERIFY CHECKSUM  
GO


USE [Turnos]
GO

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[PacienteObra]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[PacienteObra](
	[idObra] [int] NOT NULL,
	[idPaciente] [int] NOT NULL
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Especialidades]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Especialidades](
	[especialidad_id] [int] IDENTITY(1,1) NOT NULL,
	[especialidad_nombre] [varchar](200) NOT NULL,
 CONSTRAINT [PK_Especialidades] PRIMARY KEY CLUSTERED 
(
	[especialidad_id] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[MedicoxDia]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[MedicoxDia](
	[idMedicoxDia] [int] IDENTITY(1,1) NOT NULL,
	[idMedico] [int] NOT NULL,
	[idEspecialidad] [int] NOT NULL,
	[dia] [int] NOT NULL,
	[horaInicio] [int] NOT NULL,
	[horaFin] [int] NOT NULL,
	[duracionTurno] [int] NOT NULL,
	[frecuencia] [int] NOT NULL,
	[inicioFrecuencia] [int] NOT NULL,
 CONSTRAINT [PK_MedicoxDia] PRIMARY KEY CLUSTERED 
(
	[idMedicoxDia] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ObraSocial_TipoTurno]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[ObraSocial_TipoTurno](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[obraSocial] [int] NOT NULL,
	[tipoTurno] [int] NOT NULL,
	[covertura] [varchar](200) NULL,
	[observaciones] [varchar](2000) NULL,
 CONSTRAINT [PK_ObraSocial_TipoTurno] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[ObrasSociales]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[ObrasSociales](
	[obra_id] [int] IDENTITY(1,1) NOT NULL,
	[obra_nombre] [varchar](200) NOT NULL,
	[obra_mail] [varchar](200) NULL,
	[obra_telefono] [int] NULL,
	[obra_direccion] [varchar](200) NULL,
	[obra_localidad] [varchar](200) NULL,
 CONSTRAINT [PK_ObraSocial] PRIMARY KEY CLUSTERED 
(
	[obra_id] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Pacientes]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Pacientes](
	[paciente_id] [int] IDENTITY(1,1) NOT NULL,
	[paciente_nombre] [varchar](200) NOT NULL,
	[paciente_apellido] [varchar](200) NOT NULL,
	[paciente_dni] [int] NOT NULL,
	[paciente_tel] [int] NULL,
	[paciente_cel] [int] NULL,
	[paciente_mail] [varchar](200) NULL,
	[paciente_direccion] [varchar](200) NULL,
	[paciente_localidad] [varchar](200) NULL,
	[paciente_fechaAlta] [datetime] NULL,
	[paciente_observaciones] [varchar](2000) NULL,
 CONSTRAINT [PK_Pacientes] PRIMARY KEY CLUSTERED 
(
	[paciente_id] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[TipoTurnos]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[TipoTurnos](
	[idTipoTurno] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](200) NOT NULL,
 CONSTRAINT [PK_TipoTurnos] PRIMARY KEY CLUSTERED 
(
	[idTipoTurno] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Turnos]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Turnos](
	[turno_id] [int] IDENTITY(1,1) NOT NULL,
	[turno_sysdate] [datetime] NULL,
	[turno_fecha] [datetime] NOT NULL,
	[turno_paciente] [int] NOT NULL,
	[turno_medico] [int] NOT NULL,
	[turno_obraSocial] [int] NOT NULL,
	[turno_especialidad] [int] NOT NULL,
	[turno_primeraVez] [int] NULL,
	[turno_telefono] [int] NULL,
	[turno_nombre] [int] NULL,
	[turno_tipo] [int] NULL,
 CONSTRAINT [PK_Turnos] PRIMARY KEY CLUSTERED 
(
	[turno_id] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Medicos]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Medicos](
	[medico_id] [int] IDENTITY(1,1) NOT NULL,
	[medico_nombre] [varchar](200) NOT NULL,
	[medico_apellido] [varchar](200) NOT NULL,
	[medico_DNI] [int] NULL,
	[medico_telefono] [int] NULL,
	[medico_mail] [varchar](200) NULL,
 CONSTRAINT [PK_Medicos] PRIMARY KEY CLUSTERED 
(
	[medico_id] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Especialidad-Medico]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Especialidad-Medico](
	[id] [int] NOT NULL,
	[idEspecialidad] [int] NOT NULL,
	[idMedico] [int] NOT NULL,
 CONSTRAINT [PK_Especialidad-Medico] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
END
