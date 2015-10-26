using System.Windows.Forms;



namespace TurnosPEG{

    class TabPageProfesionales : TabPage {
        
       
        private DataGridView dgvElems;

         public TabPageProfesionales(System.Drawing.Size sizeTabControl)
         {
             this.Size = sizeTabControl;
             InitializeComponent();
         }

         private void InitializeComponent()
         {
             
             this.dgvElems = new System.Windows.Forms.DataGridView();
             this.dgvElems.Location = new System.Drawing.Point(70, 70);
             this.Controls.Add(this.dgvElems);
             this.Text = "Profesionales";
             this.UseVisualStyleBackColor = true;
            
         }



    }
}