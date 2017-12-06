//Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ColorRenderer extends JLabel
                           implements TableCellRenderer {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ColorRenderer(boolean isBordered) {
        setOpaque(true); //MUST do this for background to show up.
    }

	
    public Component getTableCellRendererComponent(
                            JTable table, Object color,
                            boolean isSelected, boolean hasFocus,
                            int row, int column) {
        setBackground((Color)color);
        return this;
    }
    
}