package com.jamie.raycasting.graphics.overlays;

import com.jamie.raycasting.graphics.Render;

public class StatBarOverlay extends Overlay
{
    private int barWidth, barHeight, barXStart;

    public StatBarOverlay(int width, int height, Render icon) {
        super(width, height);
        barHeight = height;
        barWidth = width - icon.width - 1;
        barXStart = icon.width + 1;

        draw(icon, 0, 0);
        fill(barXStart, 0 , width, height, 0x808080);
    }

    public void update(double fullness, int colour) {
        int fillWidth = (int) (fullness * (barWidth - 2));

        fill(barXStart + 1, 1 , barXStart + barWidth - 1, barHeight - 1, 0x000000);
        fill(barXStart + 1, 1, barXStart + fillWidth + 1, barHeight - 1, colour);
    }
}
