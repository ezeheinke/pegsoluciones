using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using Datos;
using System.Configuration;


namespace Turnos
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {

           DC dc = new DC();

            var x = (from m in dc.Medicos
                     select m);

            Medico medico = x.FirstOrDefault();

            textBox1.Text = medico.medico_apellido + ", " + medico.medico_nombre;
           
            
            

        }
    }
}
