import React, { Component } from "react";
import { Grommet, Grid, Box, Text } from "grommet";

var quiz = require("./quiz.json");
export default class App extends Component {
  componentDidMount() {
    document.title = "SinglePageGoal";
  }

  render() {
    console.log("rootLayout1");
    console.log(
      "Theme [font=SCRIPT, name=globalTheme, primaryColor=BLUE, secondaryColor=ORANGE]"
    );
    return (
      <Grid
        rows={["small", "auto"]}
        columns={[]}
        gap="large"
        areas={[
          { name: "rootLayout100", start: [0, 0], end: [0, 0] },
          { name: "rootLayout110", start: [0, 1], end: [0, 1] },
        ]}
      >
        <Box gridArea="rootLayout100" background="null">
          <Text alignSelf="center">{quiz.title}</Text>
        </Box>
        <Grid
          rows={["auto"]}
          columns={["small", "small"]}
          gap="small"
          areas={[
            { name: "middleLayout00", start: [0, 0], end: [0, 0] },
            { name: "middleLayout01", start: [1, 0], end: [1, 0] },
          ]}
        >
          <Box gridArea="middleLayout00" background="null">
            <Text alignSelf="start">'It is the left panel'</Text>
          </Box>
          <Box gridArea="middleLayout01" background="null">
            <Text alignSelf="end">'It is the right panel'</Text>
          </Box>
        </Grid>
      </Grid>
    );
  }
}
