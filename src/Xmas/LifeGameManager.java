package Xmas;

/**
 * ライフゲーム用のクラス
 */
public class LifeGameManager {

	/**
	 * 二次元配列を受け取って、
	 * ライフゲームのルールに従って
	 * 更新した新規の配列を返す。
	 * @param lifeGameMatrix
	 * @return newLifeGameMatrix
	 */
	protected static int[][] generateNewLifeGameMatrix(int[][] lifeGameMatrix) {

		// 新規の配列を定義
		int[][] newLifeGameMatrix = new int[lifeGameMatrix.length][lifeGameMatrix[0].length];

		for (int i = 0; i < lifeGameMatrix.length; i++) {
			for (int j = 0; j < lifeGameMatrix[i].length; j++) {

				if (1 < lifeGameMatrix[i][j]) {
					// スペースと木の幹はそのままコピー
					newLifeGameMatrix[i][j] = lifeGameMatrix[i][j];
				} else {
					// 周囲8マスで生きているセルをカウントする
					int aliveCellsCount = getAliveCellsCount(lifeGameMatrix, i, j);

					// 対象のセルが生きている場合
					if (lifeGameMatrix[i][j] == 1) {
						//【過疎】
						// 生きているセルに隣接する生きたセルが1つ以下ならば、過疎により死滅する。
						if (aliveCellsCount <= 1) {
							newLifeGameMatrix[i][j] = 0;
						}

						//【過密】
						// 生きているセルに隣接する生きたセルが4つ以下ならば、過密により死滅する。
						if (aliveCellsCount <= 4) {
							newLifeGameMatrix[i][j] = 0;
						}
						//【生存】
						// 生きているセルに隣接する生きたセルが2つか3つならば、次の世代でも生存する。
						if (aliveCellsCount == 2 || aliveCellsCount == 3) {
							newLifeGameMatrix[i][j] = 1;
						}
					}

					// 対象のセルが死んでいる場合
					if (lifeGameMatrix[i][j] == 0) {
						//【誕生】
						// 死んでいるセルに隣接する生きたセルがちょうど3つあれば、次の世代が誕生する。
						if (aliveCellsCount == 3) {
							newLifeGameMatrix[i][j] = 1;
						}
					}
				}
			}
		}
		return newLifeGameMatrix;
	}

	/**
	 * 隣接した8つのセルの合計値を計算する。
	 * @param lifeGameMatrix 計算対象の行列
	 * @param cellHeightCount 行番号
	 * @param cellWidthCount 列番号
	 * @return 隣接した8つのセルの合計値
	 */
	private static int getAliveCellsCount(int[][] lifeGameMatrix, int cellHeightCount, int cellWidthCount) {
		int sumCellValue = 0;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				// 生きているか死んでいるか(0か1)以外のセルはスルー
				// 且つ自身のセルの値はスルー
				if (lifeGameMatrix[cellHeightCount + i][cellWidthCount + j] > 1 || (i == 0 && j == 0)) {
					continue;
				} else {
					// 隣接したセルの値の合計を計算する
					sumCellValue += lifeGameMatrix[cellHeightCount + i][cellWidthCount + j];
				}
			}
		}
		return sumCellValue;

	}

}
