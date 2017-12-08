// Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ColorRenderer extends JLabel implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	public ColorRenderer(boolean isBordered) {
		setOpaque(true); // MUST do this for background to show up.
	}

	@Override
	public Component getTableCellRendererComponent(
			final JTable table,
			final Object color,
			final boolean isSelected,
			final boolean hasFocus,
			final int row,
			final int column)
	{
		setBackground((Color)color);
		return this;
	}
}
