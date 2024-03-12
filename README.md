# The Covering Design Problem - Thibaut Deliever


## Overview

Uncovering the Optimal Solution: An ILS Approach to the Covering Design Problem.
- **Definition:** A _(v,k,t)_ Covering Design is a collection of _k_-element subsets, called _blocks_, of 1,2,...,v, such that any _t_-element subset is contained in at _least_ one block. Each of these coverings gives an upper bound (UB) for the corresponding _C(v,k,t))_, the smallest possible number of blocks in such a Covering Design.
- **Problem:** Each _(v,k,t)_ Covering Design has a maximum upper bound. However, this upper bound contains a lot of redundancy since each _t_-subset only needs to be covered once.
- **Goal:** **Find the minimal upper bound.**


## Table of Contents
- [Abstract](#abstract)
- [Background](#background)
- [Installation](#installation)
- [Usage](#usage)
- [License](#license)


## Abstract

A metaheuristic search method implementing local search methods for finding the smallest upper bounds of _(v,k,t)_ Covering Designs, is presented. We compute upper bounds for _(v,k,t)_ Covering Designs with a focus on **v ≤ 100**, **k ≤ 25** and **t ≤ 3**, and compare our approach with the best known upper bounds. The study aims to explore the effects of a unique objective function in our implementation of the **ILS** approach and provides a framework for future work.


## Background

The covering design problem is one of the many well-studied problems in the mathematical combinatorial optimization domain. The problem definition is derived from the better known lottery wheeling systems. It has been extensively studied over the past 30 years [3,7,9] and shown notable advancements in the last decade [5]. Daniel Gordon [6] has made several contributions to the problem field including mathematical papers and a database with the best known solutions corresponding to chosen parameters (v, k, t). Gordon’s personal website [6] contains a good compact definition for a covering design: A (v, k, t)-covering design is a collection of kelement subsets, called blocks, of 1, 2, . . . , v, such that any t-element subset is contained in at least one block. The site is also mentioning the following: This site contains a collection of good (v, k, t)-coverings (known as the La Jolla Repository Tables [8]). Each of these coverings gives an upper bound for the corresponding C(v, k, t), the smallest possible number of blocks in such a covering
design. There are multiple ways [3, 5, 11, 14, 15] to calculate the covering number C(v, k, t). All of those methods can be split into two major classes: 
  1. Methods that improve the upper bound,
  2. Methods that improve the lower bound.

We make this distinction because the techniques for achieving improvements are different. The upper bound aspect of Covering Designs is more of an “optimization problem”. We start with a certain solution size x and try to optimize it to a solution size x′ < x. This can be done gradually, as in our metaheuristic approach, or more directly using a specific construction method, for example [11]. The lower bound part requires a more theoretical and abstract way of reasoning. These lower bounds are proven exactly, such as the Schonheim bound [16], which is currently a well-known method that still determines the best found lower
bound for many cases. Both calculations are necessary to find the covering number C(v, k, t). According to logical convention, that number is found when the lower bound equals the upper bound, or better formulated: we need an exact lower bound that is confirmed by a construction that can be made using the possible upper bound techniques. In cases where the covering number C(v, k, t) is not (yet) known, there is a gray area between the lower and upper bounds that can be improved by either increasing the lower bound (by proving exactly why one bound is better than the best found) or decreasing the upper bound (by demonstrating a better construction that satisfies the constraints). This research specifically focuses on improving the smallest known upper bound of (v, k, t) Covering Designs and it tries to provide a certain framework for future research into this specific problem.


## Installation

[Provide instructions on how to install and set up your thesis project. Include any dependencies or prerequisites.]

## Usage

[Explain how to use your thesis project. Provide examples and demonstrate its functionalities.]

## License

[Specify the license for your project. Choose an appropriate open-source license or specify any restrictions on usage.]

## Acknowledgements

[Optional: Acknowledge any individuals, organizations, or resources that have contributed to your thesis project.]

## Contact

[Provide contact information for inquiries or collaboration opportunities.]


## References
- [1] A Chan and R Games. (n, k, t))-covering systems and error-trapping decoding (corresp. IEEE Transactions on Information Theory, 27(5):643–646, 1981.
- [2] Charles J Colbourn. CRC handbook of combinatorial designs. CRC press, 2010.
- [3] Chaoying Dai, Pak Ching Li, and Michel Toulouse. A cooperative multilevel tabu search algorithm for the covering design problem. In Artificial Evolution: 7th International Conference, Evolution Artificielle, EA 2005, Lille, France, October 26-28, 2005, Revised Selected Papers 7, pages 119–130. Springer, 2006. 
- [4] Matthias Ehrgott. Multicriteria optimization, volume 491. Springer Science & Business Media, 2005. 
- [5] Kamal Fadlaoui and Philippe Galinier. A tabu search algorithm for the covering design problem. Journal of Heuristics, 17(6):659–674, 2011. 
- [6] Daniel Gordon. Covering designs. https://www.dmgordon.org/cover/.
- [7] Daniel Gordon. Covering designs annotated bibliography. https://www.dmgordon.org/papers/annotated_bib.pdf.
- [8] Daniel Gordon. La jolla repository tables. https://ljcr.dmgordon.org/cover/table.html.
- [9] Daniel Gordon. Lower bound improvements.
- [10] Daniel Gordon. Steiner systems. https://www.dmgordon.org/steiner/.
- [11] Daniel M Gordon, Oren Patashnik, and Greg Kuperberg. New constructions for covering designs. Journal of Combinatorial Designs, 3(4):269–284, 1995. 
- [12] Daniel Horsley. Generalising fisher’s inequality to coverings and packings. Combinatorica, 37(4):673–696, 2017.
- [13] Daniel Horsley and Rakhi Singh. New lower bounds for t-coverings. Journal of Combinatorial Designs, 26(8):369–386, 2018. 
- [14] Franc¸ois Margot. Small covering designs by branchand-cut. Mathematical Programming, 94:207–220, 2003.
- [15] Kari J Nurmela and Patric RJ Östergard. Constructing covering designs by simulated annealing. Citeseer, 1993.
- [16] Johanan Schönheim. On coverings. 1964.
- [17] Thomas Stützle and Luis Paquete. Iterated local search. https://www.metaheuristics.org/index.php%3Fmain=3&amp;sub=33.html.
