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
- [Contributing](#contributing)
- [License](#license)


## Abstract

A metaheuristic search method implementing local search methods for finding the smallest upper bounds of _(v,k,t)_ Covering Designs, is presented. We compute upper bounds for _(v,k,t)_ Covering Designs with a focus on **v ≤ 100**, **k ≤ 25** and **t ≤ 3**, and compare our approach with the best known upper bounds. The study aims to explore the effects of a unique objective function in our implementation of the **ILS** approach and provides a framework for future work.


## Background

The covering design problem is one of the many well-studied problems in the mathematical combinatorial optimization domain. The problem definition is derived from the better known lottery wheeling systems. It has been extensively studied over the past 30 years [3,7,9] and shown notable advancements in the last decade [5]. Daniel Gordon [6] has made several contributions to the problem field including mathematical papers and a database with the best known solutions corresponding to chosen parameters (v, k, t). Gordon’s personal website [6] contains a good compact definition for a covering design: A (v, k, t)-covering design is a collection of kelement subsets, called blocks, of 1, 2, . . . , v, such that any t-element subset is contained in at least one block. The site is also mentioning the following: This site contains a collection of good (v, k, t)-coverings (known as the La Jolla Repository Tables [8]). Each of these coverings gives an upper bound for the corresponding C(v, k, t), the smallest possible number of blocks in such a covering
design. There are multiple ways [3, 5, 11, 14, 15] to calculate the covering number C(v, k, t). All of those methods can be split into two major classes: 
  1. Methods that improve the upper bound,
  2. Methods that improve the lower bound.
We make this distinction because the techniques for achieving improvements are different. The upper bound aspect of Covering Designs is more of an “optimization problem”. We start with a certain solution size x and try to optimize it to a solution size x′ < x. This can be done gradually, as in our metaheuristic approach, or more directly using a specific construction method, for example [11]. The lower bound part requires a more theoretical and abstract way of reasoning. These lower bounds are proven exactly, such as the Schonheim bound [16], which is currently a well-known method that still determines the best found lower
bound for many cases. Both calculations are necessary to find the covering number C(v, k, t). According to logical convention, that number is found when the lower bound equals the upper bound, or better formulated: we need an exact lower bound that is confirmed by a construction that can be made using the possible upper bound techniques. In cases where the covering number C(v, k, t) is not (yet) known, there is a gray area between the lower and upper bounds that can be improved by either increasing the lower bound (by proving exactly why one bound is better than the best found) or decreasing the upper bound (by demonstrating a better construction that satisfies the constraints). This research specifically focuses on improving the smallest known upper bound of (v, k, t) Covering Designs and it tries to provide a certain framework for future research into this specific problem. The paper is structured as follows. Section 2 describes the metaheuristic approach we used for this research. It also contains all necessary background information on the Covering Design Problem and other relevant problems. Section 3 is dedicated to the implementation aspect of the research. Section 4 will contain experimental data and results obtained during the last phase of this study. Threats to validity, future work and a conclusion are stated in Section 5, 6 and 7 respectively.


## Installation

[Provide instructions on how to install and set up your thesis project. Include any dependencies or prerequisites.]

## Usage

[Explain how to use your thesis project. Provide examples and demonstrate its functionalities.]

## Contributing

[Explain how others can contribute to your project, such as reporting issues, suggesting improvements, or submitting pull requests.]

## License

[Specify the license for your project. Choose an appropriate open-source license or specify any restrictions on usage.]

## Acknowledgements

[Optional: Acknowledge any individuals, organizations, or resources that have contributed to your thesis project.]

## Contact

[Provide contact information for inquiries or collaboration opportunities.]

