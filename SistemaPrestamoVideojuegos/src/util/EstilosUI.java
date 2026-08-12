package util;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

public class EstilosUI {

    public static final Color FONDO =
            new Color(245, 247, 250);

    public static final Color BOTON =
            new Color(55, 95, 145);

    public static final Color BOTON_TEXTO =
            Color.WHITE;

    public static final Color TEXTO =
            new Color(35, 35, 35);

    public static final Color BORDE =
            new Color(190, 195, 205);

    public static void estilizarBoton(JButton boton) {

        boton.setBackground(BOTON);
        boton.setForeground(BOTON_TEXTO);

        boton.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        boton.setFocusPainted(false);

        boton.setBorder(
                BorderFactory.createEmptyBorder(
                        8, 18, 8, 18
                )
        );
    }

    public static void estilizarCampo(JTextField campo) {

        campo.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        campo.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDE),
                        BorderFactory.createEmptyBorder(
                                5, 8, 5, 8
                        )
                )
        );
    }

    public static void estilizarCombo(JComboBox<?> combo) {

        combo.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        combo.setBackground(Color.WHITE);
    }

    public static void estilizarTabla(JTable tabla) {

        tabla.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        tabla.setRowHeight(25);
        tabla.setGridColor(BORDE);
        tabla.setSelectionBackground(
                new Color(210, 225, 245)
        );

        tabla.setSelectionForeground(TEXTO);

        JTableHeader header = tabla.getTableHeader();

        header.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        header.setBackground(
                new Color(225, 230, 238)
        );

        header.setForeground(TEXTO);

        header.setReorderingAllowed(false);
    }
}