import React, { Component } from 'react'
import { } from './functions'
import { Grommet, Grid, Box, CheckBoxGroup, Text, Button, Clock, ResponsiveContext, TextInput } from 'grommet'
import { deepMerge } from "grommet/utils";
import { grommet } from "grommet/themes";
var data = require('./quiz.json');

export default class App extends Component {

	constructor() {
		super();
		this.state = { quiz : data }
	}

	render() {
		var customBreakpoints = deepMerge(grommet, {
			global: {
			}
		});
		var c_areas= []
		const areas = {
			default: [
				["unTruc",],
			],
		}
		const rows = {
			default:['medium','large',],
		}
		const columns = {
			default:['large',],
}
		return (
			<Grommet>
				<ResponsiveContext.Consumer>
					{size =>
					<Grid
						rows={rows[size] ? rows[size] : rows["default"]}
						columns={columns[size] ? columns[size] : columns["default"]}
					gap='null'
						areas={areas[size] ? areas[size] : areas["default"]}>
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("unTruc") >=0) ?
						<Box gridArea='unTruc' align='center' background='light-3'>
						<Text size='medium'  textAlign='center'  >{this.state.quiz.questions[this.state.quiz.indexQuestion].statement}</Text>
						<TextInput placeholder="Enter your answer here" size="medium" textAlign="center"/>
						</Box>
						:
						<Box/>
					}
				</Grid>
			}
			</ResponsiveContext.Consumer>
		</Grommet>
		);
	}
}
