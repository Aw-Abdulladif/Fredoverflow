package logic

import vm.Karel

class Problem(
    val index: String,
    val name: String,
    val story: String,
    val binaryLines: Int,
    val createWorld: () -> World
) {
    override fun toString(): String = "$index $name"

    companion object {
        // ClassNotFoundException? mvn process-resources
        @Suppress("UNCHECKED_CAST")
        val karel = Problem::class.java.classLoader.loadClass("Karel_0") as Class<out Karel>

        const val HEIGHT = 10
        const val WIDTH = 10

        const val EAST = 0
        const val NORTH = 1
        const val WEST = 2
        const val SOUTH = 3

        val emptyWorld: World = FloorPlan.empty.world()

        private fun pillars(): World {
            var world = emptyWorld

            for (x in 0..9) {
                for (y in rng.nextInt(11)..9) {
                    world = world.dropBeeper(x, y)
                }
            }
            return world
        }

        private fun randomBytes(n: Int, direction: Int): World {
            var world = FloorPlan.binary.world()

            for (y in 0 until n) {
                for (x in 2..9) {
                    if (rng.nextBoolean()) {
                        world = world.dropBeeper(x, y)
                    }
                }
            }
            return world.withKarelAt(9, 0, direction)
        }

        private val rng = java.util.Random()

        val karelsFirstProgram = Problem(
            "0.0.1",
            "karelsFirstProgram",
            "Click the GOAL button (top left)\nand watch Karel go. Drag slider\nto adjust animation speed.\nCan you program Karel to perform\nthe same steps? Test with START!",
            0
        ) {
            val world = FloorPlan.first.world()

            world.dropBeeper(1, 9).withKarelAt(0, 9, EAST)
        }

        val obtainArtifact = Problem(
            "1.1.1",
            "obtainArtifact",
            "Karel auditions for the new Indy\nmovie. To demonstrate his talent,\nKarel re-enacts the classic scene\nwhere Indy saves some valuable\nartifact from an ancient temple.",
            0
        ) {
            val world = FloorPlan.empty.builder().buildVerticalWall(5, 5).world()

            world.dropBeeper(6, 5).withKarelAt(3, 5, EAST)
        }

        val defuseOneBomb = Problem(
            "1.1.2",
            "defuseOneBomb",
            "Karel the demolition expert\ndefuses a bomb at the other end\nof the room and returns filled\nwith pride and self-confidence:\nHe did it without duplicate code!",
            0
        ) {
            val world = emptyWorld.dropBeeper(9, 9)

            world.withKarelAt(0, 9, EAST)
        }

        val defuseTwoBombs = Problem(
            "1.1.3",
            "defuseTwoBombs",
            "One bomb is no problem for Karel.\nLet's spice up the challenge!\nShouldn't this be rather simple,\ngiven that Karel already knows\nhow to defuse one single bomb?",
            0
        ) {
            val world = emptyWorld.dropBeeper(0, 0).dropBeeper(9, 9)

            world.withKarelAt(0, 9, EAST)
        }

        val practiceHomeRun = Problem(
            "1.1.4",
            "practiceHomeRun",
            "Karel's heart burns for baseball,\nbut he has become bored of just\nwatching. Tonight, he will sneak\ninto the stadium and perform his\nfirst home run. Adrenaline rush!",
            0
        ) {
            val world = emptyWorld.dropBeeper(0, 0).dropBeeper(9, 0).dropBeeper(0, 9).dropBeeper(9, 9)

            world.withKarelAt(0, 9, EAST)
        }

        val climbTheStairs = Problem(
            "1.2.1",
            "climbTheStairs",
            "Karel's elevator seems to be\nout of service as of late...\nBut Karel is still pumped from\nthe home run and full of energy!",
            0
        ) {
            val world = FloorPlan.stairs.world()

            world.withKarelAt(0, 9, EAST)
        }

        val fillTheHoles = Problem(
            "1.2.2",
            "fillTheHoles",
            "Karel considers a career in den-\ndistry. The local dental school\nhas Open House day. Coincidence?\nKarel gets to fill 4 carious\nteeth with dental amalgam. Ouch!",
            0
        ) {
            val world = FloorPlan.holes.world()

            world.withKarelAt(1, 8, EAST)
        }

        val saveTheFlower = Problem(
            "1.2.3",
            "saveTheFlower",
            "During a vacation in the alps,\nKarel discovers a rare flower\nwhich has trouble blooming\nat such low altitude...\nIt's a long way to the top!",
            0
        ) {
            val world = FloorPlan.mountain.world().dropBeeper(1, 9)

            world.withKarelAt(0, 9, EAST)
        }

        val mowTheLawn = Problem(
            "1.2.4",
            "mowTheLawn",
            "Karel promised his aunt to help\nin the garden. She has already\npulled up the weeds, so Karel\ncan focus on mowing the lawn.",
            0
        ) {
            val world = emptyWorld

            world.withBeepers(0x3f0fL, 0xc3f0fc3f0fcL.shl(20)).withKarelAt(1, 7, EAST)
        }

        val harvestTheField = Problem(
            "1.3.1",
            "harvestTheField",
            "Karel's uncle is an agricult --\nerm... farmer. Having helped his\naunt, Karel can't reject the des-\nperate plea for help on the farm.\nThe wheat is already overripe!",
            0
        ) {
            val world = emptyWorld

            world.withBeepers(0x805L, 0x2a1542a05008000L).withKarelAt(5, 7, NORTH)
        }

        val repairTheStreet = Problem(
            "1.3.2",
            "repairTheStreet",
            "Load this problem again. Notice\nsomething? Not all streets are\ncreated equal! Have you learned\nabout the if/else statement yet?\nF7..F11 are Karel's conditions.",
            0
        ) {
            val builder = FloorPlan.empty.builder()

            for (x in 0..9) {
                if (rng.nextBoolean()) {
                    builder.buildHorizontalWall(x, 9)
                } else {
                    builder.buildVerticalWall(x, 9)
                    builder.buildVerticalWall(x + 1, 9)
                }
            }
            builder.world().withKarelAt(0, 8, EAST)
        }

        val cleanTheRoom = Problem(
            "1.3.3",
            "cleanTheRoom",
            "Karel's parents are paying him\na surprise visit. His apartment\nis *really* out of shape :(\nThe chaos is almost overwhelming.\nCan you help him clean up?",
            0
        ) {
            var world = emptyWorld

            for (y in 0..9) {
                for (x in 0..9) {
                    if (rng.nextBoolean()) {
                        world = world.dropBeeper(x, y)
                    }
                }
            }
            world.withKarelAt(0, 9, EAST)
        }

        val tileTheFloor = Problem(
            "1.3.4",
            "tileTheFloor",
            "During his routine visit to the\nhardware store, Karel can't\nresist buying some flagstones.\nThey seem to be the perfect fit\nfor his luxurious bathroom!",
            0
        ) {
            emptyWorld.withKarelAt(0, 9, EAST)
        }

        val stealOlympicFire = Problem(
            "1.4.1",
            "stealOlympicFire",
            "Karel is mad with olympic fever\nand somehow his CPU decided\nit would be a good idea to\nsteal the olympic fire O_o\nLet's hope nobody will notice...",
            0
        ) {
            val world = FloorPlan.stairs.world().dropBeeper(7, 3)

            world.withKarelAt(0, 9, EAST)
        }

        val removeTheTiles = Problem(
            "1.4.2",
            "removeTheTiles",
            "The flagstones were supposed to\nbe a surprise for Karel's new\nsweetheart, Karoline. Too bad\ngreen is not her favourite color.\nOh well, back to square one...",
            0
        ) {
            val world = emptyWorld.fillWithBeepers()

            world.withKarelAt(0, 9, EAST)
        }

        val walkTheLabyrinth = Problem(
            "1.4.3",
            "walkTheLabyrinth",
            "Load this problem several times.\nNote how the generated labyrinths\nare rather simple? They contain\nneither crossroads nor dead ends.\nExactly one path to the beeper!",
            0,
            ::generateRandomLabyrinth
        )

        val hangTheLampions = Problem(
            "2.1.1",
            "hangTheLampions",
            "Today is Karel's birthday! To\ncelebrate this special occasion,\nKarel bought 10 lampions. Now all\nthat's left to do is hang them\nfrom his (irregular) ceiling.",
            0
        ) {
            val builder = FloorPlan.empty.builder()

            for (x in 0..9) {
                builder.buildHorizontalWall(x, 1 + rng.nextInt(3))
            }
            val world = builder.world().withBeepers(1023L.shl(90 - 64), 0L)
            world.withKarelAt(0, 9, EAST)
        }

        val followTheSeeds = Problem(
            "2.1.2",
            "followTheSeeds",
            "Karel had insomnia and decided\nto take a walk in the forest.\nBeing the smart robot that he is,\nhe always leaves a trail of seeds\nso he can find his way back...",
            0
        ) {
            val world = emptyWorld.withBeepers(0xffc017f50L, 0x55d5555157d405ffL)
            world.withKarelAt(5, 4, WEST)
        }

        val cleanTheTunnels = Problem(
            "2.1.3",
            "cleanTheTunnels",
            "Karel the coal miner discovers\nten tunnels of varying lengths\nfilled with valuable coal.\n(Does your solution work for\ntunnels of length 0 and 10?)",
            0
        ) {
            pillars().withKarelAt(0, 9, EAST)
        }

        val increment = Problem(
            "2.2.1",
            "increment",
            "Do you know binary numbers?\nen.wikipedia.org/wiki/Binary_number\nde.wikipedia.org/wiki/Dualsystem\nKarel wants to add 1 to a byte.\nThis is almost trivial in binary.",
            1
        ) {
            randomBytes(1, WEST)
        }

        val decrement = Problem(
            "2.2.2",
            "decrement",
            "Karel wants to subtract 1 from\na byte. Notice any similarity\nto increment? (What happens if\nKarel decrements the byte zero?\nYou can click in Karel's world!)",
            1
        ) {
            randomBytes(1, WEST)
        }

        val addSlow = Problem(
            "2.2.3",
            "addSlow",
            "Welcome to the slowest adding\nmachine in the world! Karel just\ndecrements the first byte\nand increments the second byte\nuntil the first byte is zero.",
            2
        ) {
            randomBytes(2, WEST)
        }

        val saveTheFlowers = Problem(
            "2.3.1",
            "saveTheFlowers",
            "Karel climbs Mt. Everest. On his\nway up, he collects four flowers\nthat do not get enough sunlight\non the west side of the mountain.\nEast is where the sun comes up!",
            0
        ) {
            val builder = FloorPlan.empty.builder()

            var y1 = rng.nextInt(5)
            var y2 = rng.nextInt(1 + y1)
            var y3 = rng.nextInt(1 + y2)
            var y4 = rng.nextInt(1 + y3)
            y1 += 5
            y2 += 4
            y3 += 3
            y4 += 2

            for (y in y1 until 10) builder.buildVerticalWall(1, y)
            builder.buildHorizontalWall(1, y1)
            for (y in y2 until y1) builder.buildVerticalWall(2, y)
            builder.buildHorizontalWall(2, y2)
            for (y in y3 until y2) builder.buildVerticalWall(3, y)
            builder.buildHorizontalWall(3, y3)
            for (y in y4 until y3) builder.buildVerticalWall(4, y)
            builder.buildHorizontalWall(4, y4)
            for (y in 1 until y4) builder.buildVerticalWall(5, y)

            builder.buildHorizontalWall(5, 1)

            var y7 = rng.nextInt(6)
            var y6 = rng.nextInt(1 + y7)
            var y5 = rng.nextInt(1 + y6)
            y7 += 4
            y6 += 3
            y5 += 2

            for (y in 1 until y5) builder.buildVerticalWall(6, y)
            builder.buildHorizontalWall(6, y5)
            for (y in y5 until y6) builder.buildVerticalWall(7, y)
            builder.buildHorizontalWall(7, y6)
            for (y in y6 until y7) builder.buildVerticalWall(8, y)
            builder.buildHorizontalWall(8, y7)
            for (y in y7 until 10) builder.buildVerticalWall(9, y)

            val world =
                builder.world().dropBeeper(1, y1 - 1).dropBeeper(2, y2 - 1).dropBeeper(3, y3 - 1).dropBeeper(4, y4 - 1)
            world.withKarelAt(0, 9, EAST)
        }

        val findTeddyBear = Problem(
            "2.3.2",
            "findTeddyBear",
            "In the middle of the night, Karel\nawakens from a terrible dream.\nHis teddy bear will give him\ncomfort. It should lay somewhere\nnear the edge of the bed...",
            0
        ) {
            var world = emptyWorld

            val xy = rng.nextInt(10)
            when (rng.nextInt(4)) {
                EAST -> world = world.dropBeeper(9, xy)
                WEST -> world = world.dropBeeper(0, xy)
                NORTH -> world = world.dropBeeper(xy, 0)
                SOUTH -> world = world.dropBeeper(xy, 9)
            }
            val x = rng.nextInt(10)
            val y = rng.nextInt(10)
            val dir = rng.nextInt(4)
            world.withKarelAt(x, y, dir)
        }

        val jumpTheHurdles = Problem(
            "2.3.3",
            "jumpTheHurdles",
            "Karel signs up for the Olympics\nand is allowed to participate\nin the hurdle runs. After jumping\nall the hurdles, he receives a\nspecial medal made of copper!",
            0
        ) {
            val xBeeper = 5 + rng.nextInt(5)
            val builder = FloorPlan.empty.builder()

            for (x in 1..xBeeper) {
                for (y in 0 until rng.nextInt(10)) {
                    builder.buildVerticalWall(x, 9 - y)
                }
            }
            builder.world().dropBeeper(xBeeper, 9).withKarelAt(0, 9, EAST)
        }

        val solveTheMaze = Problem(
            "2.4.1",
            "solveTheMaze",
            "Study the random mazes carefully.\nThey contain crossroads and dead\nends, but no loops. So \"always\nkeep left\" or \"always keep right\"\nshould lead Karel to the beeper!",
            0
        ) {
            val builder = FloorPlan.maze.builder()
            var world = builder.world().fillWithBeepers()

            fun generateMaze() {
                val angle = rng.nextInt(4)
                world = world.pickBeeper().turn(angle)
                repeat(4) {
                    if (world.beeperAhead()) {
                        builder.tearDownWall(world.x, world.y, world.direction)
                        world = world.moveForward()
                        generateMaze()
                        world = world.turnAround()
                        builder.tearDownWall(world.x, world.y, world.direction)
                        world = world.moveForward().turnAround()
                    }
                    world = world.turnLeft()
                }
                world = world.turn(-angle)
            }

            generateMaze()
            val x = rng.nextInt(10)
            val y = rng.nextInt(10)
            world.dropBeeper(x, y).withKarelAt(0, 0, EAST)
        }

        val quantizeBits = Problem(
            "2.4.2",
            "quantizeBits",
            "Karel the hacker is eavesdropping\non an analog communications line\nand writes down 10 bits encoded\nas 0..5 (0) or 6..10 (1). Convert\nto always 0 (0) or always 10 (1).",
            0
        ) {
            pillars().withKarelAt(0, 9, EAST)
        }

        val addFast = Problem(
            "2.4.3",
            "addFast",
            "Karel adds two bytes from the\n1st and 2nd row and stores the\nsum in the 4th row. The 3rd row\nis reserved for the carry bits.\n(Does \"carry the 1\" ring a bell?)",
            4
        ) {
            randomBytes(2, SOUTH)
        }

        val partyAgain = Problem(
            "3.1.1",
            "partyAgain",
            "Karel is preparing the next big\nparty. Unfortunately, the floor\nis so soaked from the last party\nthat he must be careful not to\nbreak through into the cellar!",
            0
        ) {
            val builder = FloorPlan.trap.builder()

            for (x in 0..9) {
                builder.buildHorizontalWall(x, 1 + rng.nextInt(3))
            }
            val world = builder.world().withBeepers(1023L.shl(80 - 64), 0L)
            world.withKarelAt(0, 8, EAST)
        }

        val fetchTheStars = Problem(
            "3.1.2",
            "fetchTheStars",
            "Karel arranges a romantic date\nwith Karoline on a frozen lake\nwhere he \"fetches the stars from\nthe sky\" (German for \"goes to the\nends of the world and back\").",
            0
        ) {
            val builder = FloorPlan.trap.builder()
            var world = builder.world()

            for (x in 0..9) {
                val y = 1 + rng.nextInt(3)
                builder.buildHorizontalWall(x, y)
                world = world.dropBeeper(x, y)
            }
            world.withKarelAt(0, 8, EAST)
        }

        val secureTheCave = Problem(
            "3.2.1",
            "secureTheCave",
            "Karel the cave explorer earns a\nliving as a tourist guide. For\nsafety measures, he breaks all\nstalactites from the ceiling and\nre-erects them as stalagmites.",
            0
        ) {
            val builder = FloorPlan.empty.builder()
            var world = builder.world()

            for (x in 0..9) {
                val y = 1 + rng.nextInt(3)
                builder.buildHorizontalWall(x, y)
                for (a in y..y + rng.nextInt(3)) {
                    world = world.dropBeeper(x, a)
                }
            }
            world.withKarelAt(0, 9, EAST)
        }

        val layAndRemoveTiles = Problem(
            "3.2.2",
            "layAndRemoveTiles",
            "Karel tries a different set of\nflagstones. But again, Karoline\nis not enamored with the outcome.\nThis time he immediately removes\nthe flagstones, in reverse order.",
            0
        ) {
            emptyWorld.withKarelAt(0, 9, EAST)
        }

        val findShelters = Problem(
            "3.3.1",
            "findShelters",
            "Karel is part of an expedition to\nthe north pole. His first task is\nfinding storm-proof shelters.\nMark Karel's path with beepers,\nbut leave the shelters empty!",
            0
        ) {
            val builder = FloorPlan.empty.builder()

            repeat(25) {
                builder.buildHorizontalWall(rng.nextInt(10), 1 + rng.nextInt(9))
                builder.buildVerticalWall(1 + rng.nextInt(9), rng.nextInt(10))
            }
            val x = rng.nextInt(10)
            val y = rng.nextInt(10)
            val dir = rng.nextInt(4)
            builder.world().withKarelAt(x, y, dir)
        }

        val addSmart = Problem(
            "3.3.2",
            "addSmart",
            "Karel adds two bytes from the\n1st and 2nd row and stores the\nsum in the 3rd row. Dropping and\nchecking carry bits is no longer\nnecessary. What a smart robot!",
            3
        ) {
            randomBytes(2, SOUTH)
        }

        val problems: List<Problem> = listOf(
            karelsFirstProgram,

            obtainArtifact,
            defuseOneBomb,
            defuseTwoBombs,
            practiceHomeRun,
            climbTheStairs,
            fillTheHoles,
            saveTheFlower,
            mowTheLawn,
            harvestTheField,
            repairTheStreet,
            cleanTheRoom,
            tileTheFloor,
            stealOlympicFire,
            removeTheTiles,
            walkTheLabyrinth,

            hangTheLampions,
            followTheSeeds,
            cleanTheTunnels,
            increment,
            decrement,
            addSlow,
            saveTheFlowers,
            findTeddyBear,
            jumpTheHurdles,
            solveTheMaze,
            quantizeBits,
            addFast,

            partyAgain,
            fetchTheStars,
            secureTheCave,
            layAndRemoveTiles,
            findShelters,
            addSmart,
        )
    }
}
