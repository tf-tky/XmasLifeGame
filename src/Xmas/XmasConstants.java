package Xmas;

/**
 * 各設定値の定数クラス
 */
public class XmasConstants {
	private XmasConstants() {};

	static final int TREE_WIDTH = 61; // ツリーの横幅(マス目の個数)を設定します
	static final double INITIAL_RATE = 30; // 何パーセントで初期に生きているか

	static final int TREE_HEIGHT = TREE_WIDTH + 1; // ツリーの縦幅(マス目の個数)を設定します

	static final int TRUNK_WIDTH = TREE_WIDTH / 7; // 幹の横幅(マス目の個数)を設定します
	static final int TRUNK_HEIGHT = TRUNK_WIDTH + 1; // 幹の縦幅(マス目の個数)を設定します

	static final int SPACE_WIDTH = 2; // 横のスペース
	static final int SPACE_HEIGHT = 2; // 縦のスペース

	static final int MATRIX_WIDTH = TREE_WIDTH + (SPACE_WIDTH * 2); // ツリー配列の列数
	static final int MATRIX_HEIGHT = TREE_HEIGHT + TRUNK_HEIGHT + (SPACE_HEIGHT * 2); // ツリー配列の行数

	static final int CENTER = (MATRIX_WIDTH - 1) / 2; // ツリーの中心になる要素数

	static final int GRID_SIZE = 10; // マスのサイズ
	static final int FRAME_WIDTH = MATRIX_WIDTH * GRID_SIZE; // フレームの横幅
	static final int FRAME_HEIGHT = MATRIX_HEIGHT * GRID_SIZE; // フレームの縦幅

}
