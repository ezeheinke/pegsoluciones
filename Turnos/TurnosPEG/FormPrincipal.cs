using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Data.Linq;


namespace TurnosPEG
{
    class FormPrincipal : Form
    {
        private TabControl tabControl;
        private TabPageConfiguracionHorarios tabHorarios;

        public FormPrincipal()
        {
            InitializeComponent();
        }
        
        private void InitializeComponent()
        {
            this.Size = new System.Drawing.Size(500, 500);
            int w = this.Size.Width - 50;
            int h = this.Size.Height - 50;
            System.Drawing.Size sizeTabControl = new System.Drawing.Size(w, h);

           
            this.tabControl = new System.Windows.Forms.TabControl();
            this.tabControl.Controls.Add(new TabPageProfesionales(sizeTabControl));
            this.tabHorarios = new TabPageConfiguracionHorarios();
            this.tabControl.Controls.Add(this.tabHorarios);
            this.Controls.Add(this.tabControl);
            this.Size = new System.Drawing.Size(500, 500);// SystemInformation.VirtualScreen.Size;

            
            this.tabControl.Size = sizeTabControl;
            this.tabControl.SuspendLayout();
            this.SuspendLayout();
            // 
            // tabHorarios
            // 
            this.tabHorarios.Location = new System.Drawing.Point(0,0);
            this.tabHorarios.Name = "tabHorarios";
            this.tabHorarios.Padding = new System.Windows.Forms.Padding(3);
            this.tabHorarios.Size = this.tabControl.Size;
            this.tabHorarios.TabIndex = 1;
            this.tabHorarios.Text = "Horarios";
            this.tabHorarios.UseVisualStyleBackColor = true;


        }
    }
}
