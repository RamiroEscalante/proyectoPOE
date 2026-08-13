package util;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

public class EstilosUI {

    // =========================
    // COLORES GENERALES
    // =========================
    public static final Color FONDO
            = new Color(242, 244, 247);

    public static final Color PANEL
            = new Color(63, 70, 78);

    public static final Color BOTON
            = new Color(55, 95, 145);

    public static final Color BOTON_HOVER
            = new Color(70, 115, 170);

    public static final Color BOTON_PELIGRO
            = new Color(170, 55, 65);

    public static final Color BOTON_PELIGRO_HOVER
            = new Color(210, 70, 80);

    public static final Color BOTON_TEXTO
            = Color.WHITE;

    public static final Color TEXTO
            = new Color(40, 45, 50);

    public static final Color TEXTO_SECUNDARIO
            = new Color(205, 210, 215);

    public static final Color BORDE
            = new Color(185, 195, 205);

    public static final Color CAMPO
            = Color.WHITE;

    public static final Color TABLA_FILA
            = Color.WHITE;

    public static final Color TABLA_HEADER
            = new Color(55, 95, 145);

    public static final Color TABLA_SELECCION
            = new Color(205, 220, 240);

    // =========================
    // BOTONES
    // =========================
    public static void estilizarBoton(JButton boton) {

        boton.setBackground(BOTON);
        boton.setForeground(BOTON_TEXTO);

        boton.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        boton.setFocusPainted(false);
        boton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        boton.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(70, 145, 220)
                        ),
                        BorderFactory.createEmptyBorder(
                                8, 18, 8, 18
                        )
                )
        );

        boton.addMouseListener(
                new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(BOTON_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(BOTON);
            }
        }
        );
    }

    public static void estilizarBotonPeligro(
            JButton boton
    ) {

        boton.setBackground(BOTON_PELIGRO);
        boton.setForeground(Color.WHITE);

        boton.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        boton.setFocusPainted(false);
        boton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        boton.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(215, 85, 95)
                        ),
                        BorderFactory.createEmptyBorder(
                                8, 18, 8, 18
                        )
                )
        );

        boton.addMouseListener(
                new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(
                        BOTON_PELIGRO_HOVER
                );
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(
                        BOTON_PELIGRO
                );
            }
        }
        );
    }

    // =========================
    // CAMPOS DE TEXTO
    // =========================
    public static void estilizarCampo(
            JTextField campo
    ) {

        campo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        campo.setBackground(CAMPO);
        campo.setForeground(TEXTO);

        campo.setCaretColor(Color.WHITE);

        campo.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDE
                        ),
                        BorderFactory.createEmptyBorder(
                                7, 10, 7, 10
                        )
                )
        );
    }

    // =========================
    // COMBO BOX
    // =========================
    public static void estilizarCombo(
            JComboBox<?> combo
    ) {

        combo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        combo.setBackground(CAMPO);
        combo.setForeground(TEXTO);

        combo.setBorder(
                BorderFactory.createLineBorder(
                        BORDE
                )
        );
    }

    // =========================
    // TABLAS
    // =========================
    public static void estilizarTabla(
            JTable tabla
    ) {

        tabla.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        tabla.setBackground(TABLA_FILA);
        tabla.setForeground(TEXTO);

        tabla.setRowHeight(28);

        tabla.setGridColor(
                new Color(55, 70, 85)
        );

        tabla.setSelectionBackground(
                TABLA_SELECCION
        );

        tabla.setSelectionForeground(
                Color.WHITE
        );

        tabla.setShowVerticalLines(true);
        tabla.setShowHorizontalLines(true);

        tabla.setFillsViewportHeight(true);

        JTableHeader header
                = tabla.getTableHeader();

        header.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        header.setBackground(
                TABLA_HEADER
        );

        header.setForeground(
                Color.WHITE
        );

        header.setReorderingAllowed(false);
    }

    // =========================
    // TITULOS
    // =========================
    public static void estilizarTitulo(
            JLabel label
    ) {

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        label.setForeground(
                new Color(90, 180, 255)
        );
    }

    public static void estilizarSubtitulo(
            JLabel label
    ) {

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        label.setForeground(
                TEXTO_SECUNDARIO
        );
    }
}
