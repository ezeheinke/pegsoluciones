using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;

using System.Data;
using Datos;

namespace TurnosPEG{

    /**
    *   Esta TabPage permitira ver un combo con los doctores, elegir uno, ver sus horarios,
    *   dias de atencion y generar un nuevo horario de atencion
    */
    class TabPageConfiguracionHorarios : TabPage{
       
        public ComboBox comboDoctores;
        public Label profesionalesText;
        private DataGridView dgvElems;
        private Button botonVer;
        
        public TabPageConfiguracionHorarios(){
            InitializeComponent();
        }

        private void InitializeComponent(){

            this.Text = "Horarios";
         

            this.comboDoctores = new ComboBox();
            this.comboDoctores.Items.AddRange(getMedicos());
            this.comboDoctores.FormattingEnabled = true;
            this.comboDoctores.Location = new System.Drawing.Point(145, 20);
            this.comboDoctores.Name = "comboDoctores";
            this.comboDoctores.TabIndex = 0;
            this.Controls.Add(this.comboDoctores);
            

            this.profesionalesText = new Label();
            this.profesionalesText.Text = "Profesionales: ";
            this.profesionalesText.Location = new System.Drawing.Point(45, 20);
            this.Controls.Add(this.profesionalesText);

            this.botonVer = new Button();
            this.botonVer.Text = "Ver horarios";
            this.botonVer.Location = new System.Drawing.Point(300, 20);

            this.botonVer.Click += new EventHandler(this.OnClickVer);
            this.Controls.Add(this.botonVer);

            this.dgvElems = new System.Windows.Forms.DataGridView();
            this.dgvElems.Size = new System.Drawing.Size(300, 300);
            this.dgvElems.Location = new System.Drawing.Point(70, 70);
            this.Controls.Add(this.dgvElems);


        }

        private static object[] getMedicos()
        {
            DC dc = new DC();

            var x = (from m in dc.Medicos
                     select m);
            object[] medicos = x.ToArray();
            return medicos;
        }


        void OnClickVer(Object sender, EventArgs e)
        {
            DC dc = new DC();

            int idMedicoSeleccionado = ((Medico)this.comboDoctores.SelectedItem).medico_id;

            var x = (from m in dc.MedicoxDias
                     where m.idMedico == idMedicoSeleccionado
                     select m
            );


            Console.Write(x);
        }

    }
}
