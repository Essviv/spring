package com.cmcc.syw.service.impl;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by sunyiwei on 2016/3/18.
 */
public class BlurImg extends RecursiveAction {
    private final int blurWidth = 3;
    private final int THRESHOLD = 1000;
    private int start;
    private int length;
    private int[] src;
    private int[] dst;

    public BlurImg(int start, int length, int[] src, int[] dst) {
        this.start = start;
        this.length = length;
        this.src = src;
        this.dst = dst;
    }

    public static void main(String[] args) {
        final int LENGTH = 1000000;

        Random r = new Random();
        int[] src = new int[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            src[i] = Math.abs(r.nextInt(255 * 255 * 255));
        }


        int[] dst = new int[LENGTH];

        BlurImg blurImg = new BlurImg(0, LENGTH, src, dst);
        ForkJoinPool fjp = new ForkJoinPool();
        fjp.invoke(blurImg);

        for (int i = 0; i < LENGTH; i++) {
            System.out.format("(%d, %d, %d) ==>  (%d, %d, %d). %n",
                    (src[i] & 0x00ff0000) >> 16,
                    (src[i] & 0x0000ff00) >> 8,
                    (src[i] & 0x000000ff),
                    (dst[i] & 0x00ff0000) >> 16,
                    (dst[i] & 0x0000ff00) >> 8,
                    (dst[i] & 0x000000ff));
        }
    }

    public int[] getDst() {
        return dst;
    }

    private void blur() {
        int nOffset = (blurWidth - 1) / 2;
        for (int i = start; i < start + length; i++) {
            float rb = 0, gb = 0, bb = 0;
            for (int j = -nOffset; j <= nOffset; j++) {
                int index = Math.min(Math.max(0, i + j), src.length - 1);
                int srcValue = src[index];
                rb += (float) ((srcValue & 0x00ff0000) >> 16) / blurWidth;
                gb += (float) ((srcValue & 0x0000ff00) >> 8) / blurWidth;
                bb += (float) ((srcValue & 0x000000ff)) / blurWidth;
            }

            dst[i] = (((int) rb) << 16)
                    | (((int) gb) << 8)
                    | (((int) bb));
        }
    }

    public void compute() {
        if (length <= THRESHOLD) {
            blur();
            return;
        }

        int split = length / 2;
        invokeAll(new BlurImg(start, split, src, dst), new BlurImg(split + start, length - split, src, dst));
    }
}

