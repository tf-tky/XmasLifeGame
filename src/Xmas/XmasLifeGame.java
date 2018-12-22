package Xmas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * メインクラス
 * クリスマスツリーっぽいものを生成して、
 * ライフゲームのルールに従ってイルミネーションを表現してみた。
 */
public class XmasLifeGame extends JPanel implements Runnable {

	private static int[][] tree; // 描画するツリーを格納する配列

	/**
	 * 初期化処理
	 * 一定確率でツリーの各セルを点灯している(生きている)か
	 * 消灯している(死んでいる)か設定する。
	 * 設定後、ツリーのイルミネーションをスタートさせる。
	 */
	private XmasLifeGame() {
		setPreferredSize(new Dimension(XmasConstants.FRAME_WIDTH, XmasConstants.FRAME_HEIGHT));

		tree = new int[XmasConstants.MATRIX_HEIGHT][XmasConstants.MATRIX_WIDTH];

		// 背景を初期化
		for (int i = 0; i < XmasConstants.MATRIX_HEIGHT; i++) {
			for (int j = 0; j < XmasConstants.MATRIX_WIDTH; j++) {
				tree[i][j] = 3;
			}
		}

		// 木の幹を初期化
		for (int i = 0; i < XmasConstants.TRUNK_HEIGHT; i++) {
			for (int j = 0; j < XmasConstants.TRUNK_WIDTH; j++) {
				tree[XmasConstants.TREE_HEIGHT + XmasConstants.SPACE_HEIGHT + i][XmasConstants.CENTER
						- XmasConstants.TRUNK_WIDTH / 2 + j] = 2;
			}
		}

		// ツリー部分を初期化
		int colCount = 0; // 行数用の変数
		for (int i = XmasConstants.SPACE_HEIGHT; i < XmasConstants.TREE_HEIGHT + XmasConstants.SPACE_HEIGHT; i++) {
			for (int j = XmasConstants.SPACE_WIDTH; j < XmasConstants.TREE_WIDTH + XmasConstants.SPACE_WIDTH; j++) {
				if (XmasConstants.CENTER - colCount <= j && j < XmasConstants.CENTER + colCount + 1) {
					if (Math.random() < XmasConstants.INITIAL_RATE / 100) {
						tree[i][j] = 1; // 生きている
					} else {
						tree[i][j] = 0; // 死んでいる
					}
				}

			}
			if (i % 2 == 1) {
				colCount++;
			}
		}

		// イルミネーションスタート！
		Thread th = new Thread(this);
		th.start();

	}

	/**
	 * イルミネーション描画メソッド
	 * 初期状態を表示した後、ツリーをライフゲームルールに従って再描画する
	 */
	public void paintComponent(Graphics g) {
		// 盤面描画
		int x = 0; // x座標
		int y = 0; // y座標

		for (int i = 0; i < XmasConstants.MATRIX_HEIGHT; i++) {
			for (int j = 0; j < XmasConstants.MATRIX_WIDTH; j++) {

				x = XmasConstants.GRID_SIZE * j;
				switch (tree[i][j]) {
				case 0:
					g.setColor(new Color(0, 170, 0)); // 死んでる場合は緑色
					break;
				case 1:
					g.setColor(Color.yellow); // 生きている場合は黄色
					break;
				case 2:
					g.setColor(new Color(99, 33, 0)); // 木の幹は茶色
					break;
				case 3:
					g.setColor(Color.black); // 背景は黒
				}
				g.fillRect(x, y, XmasConstants.GRID_SIZE, XmasConstants.GRID_SIZE);
				g.setColor(Color.black);
				g.drawRect(x, y, XmasConstants.GRID_SIZE, XmasConstants.GRID_SIZE);
			}
			y += XmasConstants.GRID_SIZE;
		}

		// ツリーをライフゲームのルールに従って更新する
		tree = LifeGameManager.generateNewLifeGameMatrix(tree);

	}

	/**
	 * JFrameを使ってツリーの描画処理を呼び出すメソッド
	 */
	private static void createXmasTree() {
		JFrame f = new JFrame("メリークリスマス…");
		f.getContentPane().setLayout(new FlowLayout());
		f.getContentPane().add(new XmasLifeGame());
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setVisible(true);
	}

	/**
	 * 150ミリ秒ごとに画面を書き換えるためのメソッド
	 */
	public void run() {
		try {
			// Thread.sleep(10000);	// 録画用待機時間
			for (int i = 0; i < 5000; i++) {
				repaint();

				Thread.sleep(150);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * メインメソッド
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createXmasTree();
			}
		});
	}
}