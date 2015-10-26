namespace Datos
{
    using System.Configuration;
    using System.Web;

    partial class DC
    {
        public DC() :
            base(ConfigurationManager.ConnectionStrings["Turnos"].ConnectionString)
            
        {
            //Este constructor es para que tome la conexion del App.Config o Web.Config
            OnCreated();
        }
    }
}