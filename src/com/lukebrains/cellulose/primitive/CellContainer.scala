package com.lukebrains.cellulose.primitive

import com.spotify.docker.client._

object CellContainer {
  val dockerClient : DockerClient = DefaultDockerClient.fromEnv().build()
  def pullContainer(container : String) =  dockerClient.pull(container)
}