package io.mem0r1es.trank.ranking

import java.net.URI

class ANC_DEPTH extends RankingAlgo {

  /**
   * Rank types by inverse-sort on the # of ANCESTORS contained in the type set,
   * weighted by their DEPTHS.
   */
  override def rank(entityTypes: Map[URI, HierInfo]): Seq[(URI, Double)] = {

    def score(hier: HierInfo): Double = {
      hier.path.filter (entityTypes.contains(_))
      .map { case uri => entityTypes(uri).level.toDouble }
      .sum
    }

    entityTypes.map {
      case (k, v) => (k, score(v))
    }.toSeq.sortBy(_._2).reverse 
  }
}
